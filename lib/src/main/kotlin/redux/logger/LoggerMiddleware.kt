package redux.logger

import redux.Dispatcher
import redux.Middleware
import redux.Store
import redux.logger.Logger.ConsoleLogger
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

class LoggerMiddleware<S : Any> : Middleware<S> {

	private val logger: Logger<S>

	private constructor(logger: Logger<S>) {
		this.logger = logger
	}

	override fun dispatch(store: Store<S>, action: Any, next: Dispatcher): Any {
		logger.log(DISPATCH, action, store.getState())
		val result = next.dispatch(action)
		logger.log(STATE, action, store.getState())
		return result
	}

	companion object {

		fun <S : Any> create(logger: Logger<S> = ConsoleLogger()): LoggerMiddleware<S> {
			return LoggerMiddleware(logger)
		}

	}
}
