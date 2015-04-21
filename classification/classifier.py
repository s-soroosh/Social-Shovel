from sklearn import svm
from sklearn import linear_model
import fetch_traindata
import re
import math

class data_preparation(object):
    def __init__(self, options):
        self.token_mapping = {}
        # for debugging
        self.reverse_token_mapping = {}
        self.token_occurence = {}
        self.num_trainingdocs = 0
        self.options = options

    def process_train_data(self, training_texts, class_labels):
        # tokenize all texts and extract feature-dicts
        text_dims = []
        self.num_trainingdocs = len(training_texts)
        for text in training_texts:
            tokens = self.tokenize(text)
            text_dim = self.get_text_vector(tokens)
            text_dims.append(text_dim)
            for dim, weight in text_dim.iteritems():
                self.token_occurence[dim] = self.token_occurence.get(dim, 0) + 1
            
        # here we have tokenized everything, we know the number dimensions in our vector space
        self.num_dimensions = max(self.token_mapping.values()) + 1

        text_vectors = []
        for text_dim in text_dims:
            tvec = self.feature_dict_to_vector(text_dim)
            text_vectors.append(tvec)

        return text_vectors, class_labels

    def process_unclassified_data(self, text):
        tokens = self.tokenize(text)
        # think about if its a problem here to modify global tokens-dict
        text_vector = self.get_text_vector(tokens, add_unknown_tokens=False)
        tvec = self.feature_dict_to_vector(text_vector)
        return tvec

    def feature_dict_to_vector(self, text_dim):
        tvec = [0] * self.num_dimensions

        vector_length = 0
        for dim, weight in text_dim.iteritems():
            tvec[dim] = weight * math.log(self.num_trainingdocs / float(self.token_occurence[dim]))
            vector_length += weight * weight
        # transform document vectors to unit length
        vector_length = math.sqrt(vector_length)
        for dim, weight in text_dim.iteritems():
            tvec[dim] = tvec[dim] / vector_length
        return tvec        

    def tokenize(self, text, bigrams = True):
        # stanford nlp?
        text = text.lower()
        # treat tags as normal words
        text = text.replace("#", " ")
        if self.options.get("smileys", True):
            # make a smiley-feature (happy/sad)
            text = text.replace(":)", " smileyhappy ")
            text = text.replace(":-)", " smileyhappy ")
            text = text.replace("=)", " smileyhappy ")
            text = text.replace(":D", " smileyhappy ")
            text = text.replace(":(", " smileysad ")
            text = text.replace(":-(", " smileysad ")
            text = text.replace("=(", " smileysad ")
            text = text.replace(";(", " smileysad ")
        # remove links
        text = re.sub(r'\w+:\/{2}[\d\w-]+(\.[\d\w-]+)*(?:(?:\/[^\s/]*))*', '', text)
        # remove usernames
        text = re.sub(r'@[A-Za-z0-9]*', '', text)

        tokens = re.findall(r"[\w']+", text)
        # add bigrams
        if bigrams:
            for i in xrange(0, len(tokens) - 1):
                tokens.append(tokens[i] + " " + tokens[i + 1])
        return tokens

    def get_text_vector(self, tokens, add_unknown_tokens=True):
        text_vector = {}
        for token in tokens:
            if token not in self.token_mapping:
                if not add_unknown_tokens:
                    continue
                dimension_id = 0
                if self.token_mapping:
                    dimension_id = max(self.token_mapping.values()) + 1
                self.token_mapping[token] = dimension_id
                self.reverse_token_mapping[dimension_id] = token

            # dimension_id : weight
            text_vector[self.token_mapping[token]] = text_vector.get(self.token_mapping[token], 0) + 1
        return text_vector

class classifier(object):
    def __init__(self):
        self.clf = None
        self.regr = None
        self.max_dimensions = 0

    def set_max_dimensions(self):
        self.max_dimensions = max(self.token_mapping.values()) + 1

    def train(self, data, class_labels):
        self.clf = svm.SVC(kernel='linear')
        self.clf.fit(data, class_labels)
#        self.regr = linear_model.LinearRegression()
#        self.regr.fit(trd.data, trd.class_labels)

    def classify(self, unknown_text_vector):
        return self.clf.predict(unknown_text_vector)
#        return self.regr.predict(unknown_text_vector)

# Trains a classifier to determine the sentiment of a text.
# The classes are Positive (2), Negative (0) and Neutral(1)
def train_sentiment():
    dp = data_preparation(options={})
    train_data, train_labels = fetch_traindata.get_data()
    text_vectors, class_labels =  dp.process_train_data(train_data, train_labels)
    
    cls = classifier()
    cls.train(text_vectors, class_labels)

    return dp, cls

# Trains a classifier to determine the topic/category of a text.
# e.g. if it's about shoes or trousers....
def train_categorization():
    #dp = data_preparation(options={"smileys":False})
    pass

def explain_result(tvec, assigned_class, data_preparation, classifier):
    print "Further explanation"
    print
    print "Document Tokens"
    for i, t in enumerate(tvec):
        if t > 0:
            print "\t", dp_sentiment.reverse_token_mapping[i], t

    dims = {}   
    for i,t in enumerate(cls_sentiment.clf.coef_[0]):
        if t != 0:
            dims[dp_sentiment.reverse_token_mapping[i]] = t
    dims = sorted(dims.items(), key=lambda x: x[1])
    print "Winning Class Model - 5 strongest modelfeatures pos/neg"
    print dims[-5:]
    print dims[0:5]

if __name__ == "__main__":
    # ============= training part ============
    dp_sentiment, cls_sentiment = train_sentiment()
    #dp_category, cls_category = train_categorization()

    # ============== classification part ======
    text3 = "I never liked #zalando"
    tvec = dp_sentiment.process_unclassified_data(text3)
    label = cls_sentiment.classify(tvec)
    print "Assigned class", label[0]
    explain_result(tvec, label, dp_sentiment, cls_sentiment)
   
 