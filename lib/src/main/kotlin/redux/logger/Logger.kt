package redux.logger

import redux.logger.Logger.Event.DISPATCH
import redux.logger.Logger.Event.STATE

/*
 * Copyright (C) 2016 Michael Pardo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

interface Logger<in S : Any> {

	fun log(event: Event, action: Any, state: S)

	enum class Event {
		DISPATCH, STATE
	}

	class ConsoleLogger<in S : Any> : Logger<S> {

		override fun log(event: Event, action: Any, state: S) {
			when (event) {
				DISPATCH -> println("Dispatching action: $action")
				STATE -> println("Next state: $state")
			}
		}

	}

}