package com.example.persistencehomework.HW22.session;

public record EntityKey<T>(Class<T> clazz, Object id) {
}
