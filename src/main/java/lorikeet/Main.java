package lorikeet;

import lorikeet.server.Server;
import lorikeet.server.ServerArgsParse;
import lorikeet.server.ServerArgsParser;

public class Main {
    public static void main(String[] args) throws Exception {

        final ServerArgsParser argParser = new ServerArgsParser();
        final ServerArgsParse result = argParser.parse(args);


        if (result instanceof ServerArgsParse.Error error) {
            System.out.println(error.error());
            System.exit(1);
        }

        if (result instanceof ServerArgsParse.OK ok) {
            new Server(ok.args()).run();
        }
    }
}
