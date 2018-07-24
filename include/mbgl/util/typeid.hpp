#pragma once

#include <atomic>

namespace mbgl {
namespace util {

class TypeID {
public:
    template <typename T>
    static uint32_t getID() {
        static std::atomic<uint32_t> count(increment());
        return count;
    }

private:
    static uint32_t increment() {
        static std::atomic<uint32_t> count(0);
        return count++;
    }

};

} // namespace util
} // namespace mbgl
