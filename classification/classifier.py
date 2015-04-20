from sklearn import svm
from sklearn import linear_model

class data_preparation(object):
    def __init__(self):
        self.token_mapping = {}

    def process_train_data(self, training_texts, class_labels):
        # tokenize all texts and extract feature-dicts
        text_dims = []
        for text in training_texts:
            tokens = self.tokenize(text)
            text_dims.append(self.get_text_vector(tokens))
            
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
        text_vector = self.get_text_vector(tokens)
        tvec = self.feature_dict_to_vector(text_vector)
        return tvec

    def feature_dict_to_vector(self, text_dim):
        tvec = [0] * self.num_dimensions
        for dim, weight in text_dim.iteritems():
            tvec[dim] = weight
        return tvec        

    def tokenize(self, text):
        # stanford nlp
        text = text.lower()
        return text.split(" ")

    def get_text_vector(self, tokens):
        text_vector = {}
        for token in tokens:
            if token not in self.token_mapping:
                dimension_id = 0
                if self.token_mapping:
                    dimension_id = max(self.token_mapping.values()) + 1
                self.token_mapping[token] = dimension_id

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

if __name__ == "__main__":
    # ============= training part ============
    text1 = "zalando is super incredible awesome the best"
    text2 = "zalando is fucking shit worst shop"

    dp = data_preparation()
    text_vectors, class_labels = dp.process_train_data([text1, text2], [1,0])

    cls = classifier()
    cls.train(text_vectors, class_labels)

    # ============== classification part ======
    text3 = "zalando is fucking incredible awesome"
    tvec = dp.process_unclassified_data(text3)
    print cls.classify(tvec)
     
