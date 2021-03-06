package liquibase.ant;

import liquibase.Liquibase;
import org.apache.tools.ant.BuildException;

import java.io.Writer;

public class ChangeLogSyncTask extends BaseLiquibaseTask {

    public void execute() throws BuildException {

        Liquibase liquibase = null;
        try {
            liquibase = createLiquibase();

            Writer writer = createOutputWriter();
            if (writer == null) {
                liquibase.changeLogSync(getContexts());
            } else {
                liquibase.changeLogSync(getContexts(), writer);
                writer.flush();
                writer.close();
            }

        } catch (Exception e) {
            throw new BuildException(e);
        } finally {
            closeDatabase(liquibase);
        }
    }
}