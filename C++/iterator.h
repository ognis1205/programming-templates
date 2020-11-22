/*
 * Note: This template uses some c++11 functions, so you have to compile it with c++11 flag.
 *       Example:-   $ g++ -std=c++11 foo.cc
 *
 * Copyright (c) 2020-present, Shingo OKAWA.
 * All rights reserved.
 */
#include <type_traits>

namespace ognis {
namespace types {

  using namespace std;

  struct null_sentinal_t {
    template<typename Rhs, typename enable_if<!is_same<Rhs, null_sentinal_t>::value, nullptr_t>::type=nullptr>
    friend bool operator==(const Rhs& rhs, null_sentinal_t) {
      return !*rhs;
    }

    template<typename Rhs, typename enable_if<!is_same<Rhs, null_sentinal_t>::value, nullptr_t>::type=nullptr>
    friend bool operator!=(const Rhs& rhs, null_sentinal_t) {
      return !(rhs == null_sentinal_t{});
    }

    template<typename Lhs, typename enable_if<!is_same<Lhs, null_sentinal_t>::value, nullptr_t>::type=nullptr>
    friend bool operator==(null_sentinal_t, const Lhs& lhs) {
      return !*lhs;
    }

    template<typename Lhs, typename enable_if<!is_same<Lhs, null_sentinal_t>::value, nullptr_t>::type=nullptr>
    friend bool operator!=(null_sentinal_t, const Lhs& lhs) {
      return !(null_sentinal_t{} == lhs);
    }

    friend bool operator==(null_sentinal_t, null_sentinal_t) {
      return true;
    }

    friend bool operator!=(null_sentinal_t, null_sentinal_t) {
      return false;
    }
  };

} // end of types.
} // end of ognis.
