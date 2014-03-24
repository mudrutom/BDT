package cz.cvut.bigdata.cli;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Map.Entry;

public class ConfiguredTool extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		final Configuration config = getConf();

		System.out.printf("%60s     %s\n", "Property", "Value");
		System.out.println("------------------------------------------------------------------------------------------");

		final Configuration defaultConfig = new Configuration(true);
		final Boolean showDefaults = config.getBoolean("show.default.properties", false);

		for (Entry<String, String> entry : config) {
			String defaultValue = defaultConfig.get(entry.getKey());
			if (showDefaults || !entry.getValue().equals(defaultValue)) {
				System.out.printf("%60s     %s\n", entry.getKey(), entry.getValue());
			}
		}

		if (args.length == 0) {
			System.out.println("There were no additional arguments.");
		} else {
			System.out.println("Remaining Arguments");
			System.out.println("-------------------");

			System.out.print(args[0]);
			for (int i = 1; i < args.length; i++) {
				System.out.print(" " + args[i]);
			}
			System.out.println();
		}

		return 0;
	}

	public static void main(String[] arguments) throws Exception {
		System.exit(ToolRunner.run(new ConfiguredTool(), arguments));
	}
}
