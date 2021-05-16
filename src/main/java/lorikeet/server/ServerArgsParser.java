package lorikeet.server;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.Optional;

public class ServerArgsParser {
    private final Options options;

    public ServerArgsParser() {
        this.options = new Options();

        final Option jar = new Option("j", "--system-jar", true, "the jar file of the entire system to run");
        jar.setRequired(true);
        options.addOption(jar);

        final Option interactive = new Option("i", "interactive", false, "run the server in interactive mode");
        options.addOption(interactive);
    }

    public ServerArgsParse parse(String[] args) {
        CommandLineParser parser = new DefaultParser();
        final HelpFormatter formatter = new HelpFormatter();
        try {
            final CommandLine cmd = parser.parse(options, args);
            return new ServerArgsParse.OK(new ServerArgs(
                cmd.getOptionValue("j"),
                cmd.hasOption("i")
            ));
        } catch (ParseException e) {
            return new ServerArgsParse.Error(e.getMessage());
        }
    }

}
