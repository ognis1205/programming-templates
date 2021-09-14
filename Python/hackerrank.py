"""$File: #FILENAME#, $Timestamp: #TIMESTAMP#
"""

from __future__ import absolute_import
from __future__ import division
from __future__ import print_function
from __future__ import unicode_literals

try:
   input = raw_input
except NameError:
   pass

import io
import os
import sys
from copy import deepcopy
from math import sqrt
from itertools import permutations
from itertools import combinations
from textwrap import dedent
from traceback import format_exc


class Input(object):
    def __init__(self, text=None):
        self._io = io.StringIO(text) if text else None

    def readline(self, parser=str, is_array=False):
        return self._parse(parser) if is_array else self._parse(parser)[0]

    def _parse(self, parser):
        return map(parser, self._readline().split())

    def _readline(self):
        return self._io.readline().strip() if self._io else input()

    def __enter__(self):
        return self

    def __exit__(self, *_):
        if self._io:
            self._io.close()


def array(n, generate=lambda i: 0):
    return [deepcopy(generate(i)) for i in range(n)]


def #FILENAME():
    INPUT = dedent("""\
    """)

    with Input(INPUT) as input_file:
        pass


if __name__ == "__main__":
   try:
      #FILENAME()
   except:
      print(format_exc(), file=sys.stderr)
