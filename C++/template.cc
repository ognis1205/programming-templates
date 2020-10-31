/*
 * Note: This template uses some c++11 functions , so you have to compile it with c++11 flag.
 *       Example:-   $ g++ -std=c++11 foo.cc
 *
 * Author : Shingo OKAWA
 */
#include <iostream>
#include <iomanip>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <queue>
#include <deque>
#include <bitset>
#include <iterator>
#include <list>
#include <stack>
#include <map>
#include <set>
#include <functional>
#include <numeric>
#include <utility>
#include <limits>
#include <time.h>
#include <math.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>

using namespace std;

/*
 * All Required 'define' Pre-Processors and 'typedef' Constants.
 */
#define STR(args...) #args
#define CAT(x, y) x ## y
#define SD(x) scanf("%d", &x)
#define SLD(x) scanf("%ld", &x)
#define SLLD(x) scanf("%lld", &x)
#define SC(x) scanf("%c", &x)
#define SS(x) scanf("%s", &x)
#define SF(x) scanf("%f", &x)
#define SLF(x) scanf("%lf", &x)
#define MEMSET(x, y) memset(x, (y), sizeof(x))
#define MP make_pair
#define PB push_back
#define FOR(i, j, k, inc) for (i64 i = j; i < k; i += inc)
#define RFOR(i, j, k, dec) for (i64 i = j; i >= k; i -= dec)
#define REP(i, j) FOR(i, 0, j, 1)
#define RREP(i, j) RFOR(i, j, 0, 1)
#define ALL(cont) cont.begin(), cont.end()
#define RALL(cont) cont.end(), cont.begin()
#define FOREACH(it, l) for (auto it = l.begin(); it != l.end(); it++)
#define ASSERT(expr...) assert((expr))
#define IN(x, y, z) y <= x && x <= z

using i8 = int8_t;
using u8 = uint8_t;
using i16 = int16_t;
using u16 = uint16_t;
using i32 = int32_t;
using u32 = uint32_t;
using i64 = int64_t;
using u64 = uint64_t;
using f32 = float;
using f64 = double;

constexpr i64 kInf = 1010000000000000017LL;
constexpr i64 kMod = 1000000007LL;
constexpr f64 kEps = 1e-12;
constexpr f64 kPi = 3.14159265358979323846;

/*
 * Templates of Some Basic Operations.
 */
template<typename T, typename U>
inline void AMin(T &t, U u) {
  if (u < t) t = u;
}

template<typename T, typename U>
inline void AMax(T &t, U u) {
  if (t < u) t = u;
}

template<typename T>
inline i64 SizeOf(T (&t)) {
  return static_cast<i64>(t.size());
}

template<typename T, size_t N>
inline i64 SizeOf(T (&t)[N]) {
  return static_cast<i64>(N);
}

/*
 * Debugging Class Template.
 */
#ifdef LOCAL
string to_string(char c) {
  return "'" + string({c}) + "'";
}

string to_string(bool b) {
  return b ? "true" : "false";
}

string to_string(const string &s) {
  string ret = "";
  for (i64 i = 0; i < SizeOf(s); ++i) {
    ret += s[i];
  }
  return '"' + ret + '"';
}

string to_string(const char *cs) {
  string ret(cs);
  return to_string(ret);
}

template<size_t N>
string to_string(const bitset<N> &bs) {
  string ret = "";
  for (i64 i = 0; i < SizeOf(bs); ++i) {
    ret += bs[i] + '0';
  }
  return to_string(ret);
}

template<typename T>
string to_string(const T (&t));

template<typename T, typename U>
string to_string(const pair<T, U> &p) {
  return "(" + to_string(p.first) + ", " + to_string(p.second) + ")";
}

template<typename T>
string to_string(const T (&t)) {
  i64 start = 0, end = kInf;
  string ret = "[";
  auto cur = begin(t);
  advance(cur, min(start, SizeOf(t)));
  for(i64 i = min(start, SizeOf(t)), j = min(end, SizeOf(t) - 1); i <= j; ++i) {
    ret += to_string(*cur) + (i != j ? ", " : "");
    cur = next(cur);
  }
  return ret + "]";
}

template<i64 Index, typename... Ts>
struct PrintTuple {
  string operator() (const tuple<Ts...>& t) {
    string ret = PrintTuple<Index - 1, Ts...>{}(t);
    ret += (Index ? ", " : "");
    return ret + to_string(get<Index>(t));
  }
};

template<typename... Ts>
struct PrintTuple<0, Ts...> {
  string operator() (const tuple<Ts...>& t) {
    return to_string(get<0>(t));
  }
};

template<typename... Ts>
string to_string(const tuple<Ts...>& t) {
  const auto Size = tuple_size<tuple<Ts...>>::value;
  return "(" + PrintTuple<Size - 1, Ts...>{}(t) + ")";
}

void Debug() {}

template<typename Heads, typename... Tails>
void Debug(Heads h, Tails... t) {
  const auto Size = sizeof...(Tails);
  cout << to_string(h) << (Size  ? ", " : "");
  Debug(t...);
}
#  define VER(...) do {\
                     cout << "GCC version is " << __GNUC__ << "." << __GNUC_MINOR__ << "." << __GNUC_PATCHLEVEL__ << endl; \
                     cout << "C++ version is " << __cplusplus << endl;\
                   } while (0)
#  define DBG(...) cout << "[DEBUG]\t" << #__VA_ARGS__ << ": "; Debug(__VA_ARGS__); cout << endl;
#else
#  define VER(...)
#  define DBG(...)
#endif

/*
 * Initialization Settings and User-defined Variables.
 */
struct Init {
  static constexpr int kIosPrecision = 15;
  static constexpr bool kAutoFlush = false;

  Init() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);
    cout << fixed << setprecision(kIosPrecision);
    if (kAutoFlush) cout << unitbuf;
  }
} INIT;

i64 n = 0;
i64 m = 1;
vector<i64> k;

/*
 * User-defined Functions.
 */
void Solve() {
  DBG(n, m, k);
  return;
}

/*
 * Main Function.
 */
int main(int argc, char* argv[]) {
  Solve();
  return 0;
}
