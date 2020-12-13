#!/usr/bin/env python
# $File: #FILENAME#, $Timestamp: #TIMESTAMP#
from __future__ import absolute_import
from __future__ import division
from __future__ import print_function
from __future__ import unicode_literals

import os
import sys
import numpy as np
import scipy as sp
from collections import *
from copy import *
from itertools import *
from math import *
from traceback import *
from optparse import OptionParser


class Stdin(object):
    def __init__(self, path):
        self._stdin = sys.stdin
        self._path = path

    def __enter__(self):
        if self._path is not None:
            sys.stdin = open(self._path, 'r')
        return self

    def __exit__(self, type, value, traceback):
        if self._path is not None:
            sys.stdin.close()
        sys.stdin = self._stdin

class Stdout(object):
    def __init__(self, path):
        self._stdout = sys.stdout
        self._path = path

    def __enter__(self):
        if self._path is not None:
            sys.stdout = open(self._path, 'w')
        return self

    def __exit__(self, type, value, traceback):
        if self._path is not None:
            sys.stdout.close()
        sys.stdout = self._stdout

def get_line():
    return input()

def get_line_as(parse, in_array=False):
    return map(parse, get_line().split()) if in_array else parse(get_line())

def debug(*args):
    def name(obj, ns=globals()):
        try:
            return [n for n in ns if ns[n] is obj][0]
        except IndexError:
            return str(obj)
    if __debug__:
        print(', '.join(map(lambda x: '{}: {}'.format(name(x), x), args)), file=sys.stderr)

def array(n, value=0):
    return [deepcopy(value) for i in range(n)]

def parse_args():
    parser = OptionParser()
    parser.add_option(
        "-i", "--input",
        dest="input", default=None,
        metavar="INPUT_FILE_PATH")
    parser.add_option(
        "-o", "--output",
        dest="output", default=None,
        metavar="OUTPUT_FILE_PATH")
    return parser.parse_args()

def main():
    pass


if __name__ == "__main__":
    try:
        options, _ = parse_args()
        with Stdin(options.input), Stdout(options.output):
            debug()
            main()
    except:
        print(format_exc(), file=sys.stderr)
