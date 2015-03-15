# Overview #
EclEmma is a free Java code coverage tool for Eclipse, available under the Eclipse Public License. Internally it is based on the great EMMA Java code coverage tool, trying to adopt EMMA's philosophy for the Eclipse workbench:

  * Fast develop/test cycle: Launches from within the workbench like JUnit test runs can directly be analyzed for code coverage.
  * Rich coverage analysis: Coverage results are immediately summarized and highlighted in the Java source code editors.
  * Non-invasive: EclEmma does not require modifying your projects or performing any other setup.

The Eclipse integration has its focus on supporting the individual developer in an highly interactive way.

The update site for EclEmma is http://update.eclemma.org/. For the latest Eclipse version EclEmma is also available via the Marketplace Client, simply search for "EclEmma".
Features
Launching

EclEmma adds a so called launch mode to the Eclipse workbench. It is called Coverage mode and works exactly like the existing Run and Debug modes. The Coverage launch mode can be activated from the Run menu or the workbench's toolbar:

Launching Toolbar

Simply launch your applications or unit tests in the Coverage mode to collect coverage information. Currently the following launch types are supported:

  * Local Java application
  * Eclipse/RCP application
  * Equinox OSGi framework
  * JUnit test
  * TestNG test
  * JUnit plug-in test
  * JUnit RAP test
  * SWTBot test

Analysis

After your application or unit test has terminated code coverage information is automatically available in the Eclipse workbench:

  * Coverage overview: The Coverage view lists coverage summaries for your Java projects, allowing drill-down to method level.
  * Source highlighting: The result of a coverage session is also directly visible in the Java source editors. A customizable color code highlights fully, partly and not covered lines. This works for your own source code as well as for source attached to instrumented external libraries.

Additional features support analysis for your test coverage:

  * Different counters: Select whether instructions, lines, basic blocks, methods or loaded types should be summarized.
  * Multiple coverage sessions: Switching between coverage data from multiple sessions is possible.
  * Merge Sessions: If multiple different test runs should be considered for analysis coverage sessions can easily be merged.

Import/Export

While EclEmma is primarily designed for test runs and analysis within the Eclipse workbench, it provides some import/export features.

  * Coverage data import: A wizard allows to import **.ec coverage data files from external launches.
  * Coverage report export: Coverage data can be exported as a**.ec file or in XML or HTML format.