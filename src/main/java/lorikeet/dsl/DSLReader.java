package lorikeet.dsl;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.net.URL;

public class DSLReader {
    final GroovyScriptEngine scriptEngine;

    public DSLReader(URL root) {
        this.scriptEngine = new GroovyScriptEngine(new URL[]{root});
    }

    public SubSystemDSLSpec read(String file) {
        final Binding binding = new Binding();
        final SubSystemDSLSpec spec = new SubSystemDSLSpec();
        try {
            scriptEngine.run(file, spec.addSpecBindings(binding));
        } catch (ResourceException | ScriptException e) {
            e.printStackTrace();
        }
        return spec;
    }
}
