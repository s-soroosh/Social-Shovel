__author__ = 'soroosh'

import time
import server
import config
import json
import stomp
import classifier

class ClassificationListener(object):
    def __init__(self, con):
        self.con = con
        self.opinions = { 0 : "UNSATISFIED", 1 : "SATISFIED", -1 : "NEUTRAL" }

    def on_error(self, headers, message):
        json.load(message)
        print('received an error %s' % message)

    def on_message(self, headers, message):
        print('% received' % message)
        message_dict = json.loads(message)
        text = message_dict['content']

        # opinion classification
        tvec = classifier.dp_sentiment.process_unclassified_data(text)
        label = classifier.cls_sentiment.classify(tvec)
        label_name = self.opinions[label]

        # category classification
        

        print "tweet: ", text
        print "opinion: ", label_name
        print "category: "
        print

        message_dict['userOpinion'] = label_name
        message_dict['messageClass'] = 'SHOE'
        serialized_obj = json.dumps(message_dict)

        self.con.send(body=serialized_obj, destination=config.out_queue)


conn = stomp.Connection()
conn.set_listener('', ClassificationListener(conn))
conn.start()
conn.connect()

conn.subscribe(destination=config.in_queue, id=1, ack='auto')

# conn.send(body=' '.join(sys.argv[1:]), destination='/topic/analyzed-message')
server.Server().start()
conn.disconnect()
