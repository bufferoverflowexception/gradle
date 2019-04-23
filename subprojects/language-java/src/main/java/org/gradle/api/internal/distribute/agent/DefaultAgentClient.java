package org.gradle.api.internal.distribute.agent;

/**
 * Created by nls on 2019/4/21. Nothing.
 */
public class DefaultAgentClient implements AgentClient {

    public int getAliveAgentCount() {
        return 0;
    }

    public int getCanUseAgentCount() {
        return 0;
    }
}