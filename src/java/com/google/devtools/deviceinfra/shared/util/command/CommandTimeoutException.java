/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.deviceinfra.shared.util.command;

import com.google.devtools.deviceinfra.api.error.id.defined.BasicErrorId;
import java.time.Duration;
import javax.annotation.Nullable;

/**
 * Checked exception thrown when a command completes and if it timeouts/start-timeouts no matter if
 * it succeeds.
 *
 * @see Command
 * @see Command#timeout
 * @see Command#startTimeout
 */
public class CommandTimeoutException extends CommandExecutionException {

  CommandTimeoutException(
      Command command,
      Duration finalizedTimeout,
      @Nullable Duration finalizedStartTimeout,
      CommandResult commandResult) {
    super(
        BasicErrorId.CMD_EXEC_TIMEOUT,
        String.format(
            "Command timeout (timeout=%s%s)",
            finalizedTimeout,
            finalizedStartTimeout == null
                ? ""
                : String.format(", start_timeout=%s", finalizedStartTimeout)),
        /*cause=*/ null,
        command,
        commandResult);
  }
}
