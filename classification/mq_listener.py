__author__ = 'soroosh'

import time
import server
import config
import json
import stomp


class MyListener(object):
    def __init__(self, con):
        self.con = con

    def on_error(self, headers, message):
        json.load(message)
        print('received an error %s' % message)

    def on_message(self, headers, message):
        print('% received' % message)
        message_dict = json.loads(message)
        # SATISFIED,
        # NEUTRIAL,
        # UNSATISFIED
        message_dict['userOpinion'] = 'SATISFIED'
        #Write some constants
        message_dict['messageClass'] = 'SHOE'
        serialized_obj = json.dumps(message_dict)

        self.con.send(body=serialized_obj, destination=config.out_queue)


conn = stomp.Connection()
conn.set_listener('', MyListener(conn))
conn.start()
conn.connect()

conn.subscribe(destination=config.in_queue, id=1, ack='auto')

# conn.send(body=' '.join(sys.argv[1:]), destination='/topic/analyzed-message')
server.Server().start()
conn.disconnect()