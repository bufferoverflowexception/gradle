
package org.gradle.api.internal.distribute.protocol;

/**
 * Created by nls on 2019/4/21. Nothing.
 */
public interface IProtocol {

    boolean decode(byte[] data);

    byte[] encode();
}