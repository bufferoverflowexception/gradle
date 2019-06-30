
package org.gradle.api.internal.distribute.compile;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.gradle.api.internal.distribute.agent.AgentClient;

/**
 * Created by nls on 2019/4/21. Nothing.
 */
public class CompileDispatcher {

    private AgentClient agentClient;
    private IFactory iFactory;
    private DispatchTaskTrack taskTrack;
    // private Map<String, Set<String>>

    public CompileDispatcher(AgentClient client, IFactory iFactory) {
        this.agentClient = client;
        this.iFactory = iFactory;
    }

    public boolean dispatch(Map<String, Set<String>> recompileClass) {
        int agentCount = agentClient.getCanUseAgentCount();
        int count = 0;
        Iterator<Map.Entry<String, Set<String>>> it = recompileClass.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Set<String>> entry = it.next();
            DispatchTask task = iFactory.create(entry.getKey(), entry.getValue());
            taskTrack.runTask(task);
            if (++count > agentCount) {
                break;
            }
            it.remove();
        }
        return true;
    }
}