__author__ = 'soroosh'

import time
import sys

import stomp

class MyListener(object):
    def on_error(self, headers, message):
        print('received an error %s' % message)
    def on_message(self, headers, message):
        print('received a message %s' % message)

conn = stomp.Connection()
conn.set_listener('', MyListener())
conn.start()
conn.connect()

conn.subscribe(destination='/topic/message', id=1, ack='auto')

# conn.send(body=' '.join(sys.argv[1:]), destination='/topic/analyzed-message')

time.sleep(2000)
conn.disconnect()