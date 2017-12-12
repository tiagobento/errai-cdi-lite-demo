/*
 * Copyright (C) 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.demos.cdi.lite.todolist.textual;

import org.jboss.errai.common.client.api.annotations.IOCProducer;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tiago Bento <tfernand@redhat.com>
 */
public class KeyListener {

  private static final KeyListener k = new KeyListener();

  @IOCProducer
  public static KeyListener keyListener() {
    return k;
  }

  private static String ttyConfig;
  private final List<KeyPressSensitive> subscribers = new ArrayList<>();
  private final Thread thread;

  public KeyListener() {
    thread = new Thread(this::listenToKeys);
  }

  void registerSubscriber(final KeyPressSensitive k) {
    subscribers.add(k);
  }

  void deregisterSubscriber(final KeyPressSensitive k) {
    subscribers.remove(k);
  }

  public void start() {
    thread.start();
  }

  private static void setTerminalToCBreak() throws IOException, InterruptedException {
    ttyConfig = stty("-g");
    stty("-icanon min 1"); // set the console to be character-buffered instead of line-buffered
    stty("-echo"); // disable character echoing
  }

  // Execute the stty command with the specified arguments against the current active terminal.
  private static String stty(final String args) throws IOException, InterruptedException {
    return exec(new String[] { "sh", "-c", "stty " + args + " < /dev/tty" });
  }

  // Execute the specified command and return the output (both stdout and stderr).
  private static String exec(final String[] cmd) throws IOException, InterruptedException {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();

    Process p = Runtime.getRuntime().exec(cmd);
    int c;
    InputStream in = p.getInputStream();

    while ((c = in.read()) != -1) {
      bout.write(c);
    }

    in = p.getErrorStream();

    while ((c = in.read()) != -1) {
      bout.write(c);
    }

    p.waitFor();

    return new String(bout.toByteArray());
  }

  private void listenToKeys() {
    try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
      setTerminalToCBreak();

      while (true) {
        final char c = (char) input.read();
        for (int i = 0; i < subscribers.size(); i++) {
          subscribers.get(i).onKeyPressed(Character.toLowerCase(c));
        }
      }

    } catch (final Exception e) {
      throw new RuntimeException("Error while listening to keys", e);
    } finally {
      try {
        System.out.println("Bye..");
        stty(ttyConfig.trim());
      } catch (final Exception e) {
        System.err.println("Exception restoring tty config");
      }
    }
  }

  public void stop() {
    thread.stop();
  }
}
