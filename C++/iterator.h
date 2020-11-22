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

  struct SentinalType {
    template<typename Rhs, typename enable_if<!is_same<Rhs, SentinalType>::value, nullptr_t>::type=nullptr>
    friend bool operator==(const Rhs& rhs, SentinalType) {
      return !*rhs;
    }

    template<typename Rhs, typename enable_if<!is_same<Rhs, SentinalType>::value, nullptr_t>::type=nullptr>
    friend bool operator!=(const Rhs& rhs, SentinalType) {
      return !(rhs == SentinalType{});
    }

    template<typename Lhs, typename enable_if<!is_same<Lhs, SentinalType>::value, nullptr_t>::type=nullptr>
    friend bool operator==(SentinalType, const Lhs& lhs) {
      return !*lhs;
    }

    template<typename Lhs, typename enable_if<!is_same<Lhs, SentinalType>::value, nullptr_t>::type=nullptr>
    friend bool operator!=(SentinalType, const Lhs& lhs) {
      return !(SentinalType{} == lhs);
    }

    friend bool operator==(SentinalType, SentinalType) {
      return true;
    }

    friend bool operator!=(SentinalType, SentinalType) {
      return false;
    }
  };

} // end of types.
} // end of ognis.
