package fr.unice.polytech.qgl.tests.mock.internal.mocks;

import java.util.Arrays;
import java.util.Objects;

public class Invocation {

    private ConfigurableObject proxy;
    private String method;
    private Object[] args;


    public Invocation(ConfigurableObject obj, String method, Object[] args) {
        this.proxy = obj;
        this.method = method;
        this.args = args;
    }

    Invocation(String method, Object[] args) { this(null, method, args); }

    public String getMethod() { return method; }

    public void returns(Object value) {
        this.proxy.registerInvocation(this, value);
    }

    public void defaultsTo(Object defaultValue) {
        this.proxy.registerDefaultValue(method, defaultValue);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invocation that = (Invocation) o;
        return Objects.equals(method, that.method) && Arrays.equals(args, that.args);
    }

    @Override public int hashCode() {
        return 31 * Objects.hash(method) + Arrays.hashCode(args);
    }
}