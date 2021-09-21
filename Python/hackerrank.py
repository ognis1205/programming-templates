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
from itertools import (
    combinations,
    combinations_with_replacement,
    islice,
    permutations
)
from textwrap import dedent
from traceback import format_exc


class Input(object):
    def __init__(self, text=None):
        self._io = io.StringIO(text) if text else sys.stdin

    def readline(self, parser=str, is_array=False):
        return map(parser, self._readline().split()) if is_array else parser(self._readline())

    def _readline(self):
        return self._io.readline().strip()

    def __iter__(self):
        return iter(map(lambda x: x.strip(), self._io))

    def __enter__(self):
        return self

    def __exit__(self, *_):
        if self._io:
            self._io.close()


def array(n, generate=lambda i: 0):
    return [deepcopy(generate(i)) for i in range(n)]


INPUT = dedent("""\
This is a placeholder for input.
You can mock stdio here.
123 456
123
""")


def #FILENAME#():
    with Input(INPUT) as input_file:
        print(input_file.readline())
        print(input_file.readline())
        print(input_file.readline(int, is_array=True))
        print(input_file.readline(int))


if __name__ == "__main__":
   try:
      #FILENAME#()
   except:
      print(format_exc(), file=sys.stderr)
