
package org.gradle.api.internal.distribute.compile;

import java.util.Map;
import java.util.Set;

import org.gradle.api.internal.distribute.agent.AgentClient;

/**
 * Created by nls on 2019/4/21. Nothing.
 */
public class CompileDispatcher {

    private AgentClient agentClient;
    private IFactory iFactory;

    public CompileDispatcher(AgentClient client, IFactory iFactory) {
        this.agentClient = client;
        this.iFactory = iFactory;
    }

    public boolean dispatch(Map<String, Set<String>> recompileClass) {
        int agentCount = agentClient.getCanUseAgentCount();

        return true;
    }
}