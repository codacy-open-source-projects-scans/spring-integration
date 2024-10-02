/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.jpa.inbound;

import org.junit.Test;

import org.springframework.integration.jpa.core.JpaExecutor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Gunnar Hillert
 * @author Artem Bilan
 *
 * @since 2.2
 *
 */
public class JpaPollingChannelAdapterUnitTests {

	@Test
	public void testReceiveNull() {
		JpaExecutor jpaExecutor = mock(JpaExecutor.class);

		when(jpaExecutor.poll()).thenReturn(null);

		JpaPollingChannelAdapter jpaPollingChannelAdapter = new JpaPollingChannelAdapter(jpaExecutor);
		assertThat(jpaPollingChannelAdapter.receive()).isNull();
	}

	@Test
	public void testReceiveNotNull() {
		JpaExecutor jpaExecutor = mock(JpaExecutor.class);

		when(jpaExecutor.poll()).thenReturn("Spring");

		JpaPollingChannelAdapter jpaPollingChannelAdapter = new JpaPollingChannelAdapter(jpaExecutor);
		assertThat(jpaPollingChannelAdapter.receive()).as("Expecting a Message to be returned.").isNotNull();
		assertThat(jpaPollingChannelAdapter.receive().getPayload()).isEqualTo("Spring");
	}

	@Test
	public void testGetComponentType() {
		JpaExecutor jpaExecutor = mock(JpaExecutor.class);

		JpaPollingChannelAdapter jpaPollingChannelAdapter = new JpaPollingChannelAdapter(jpaExecutor);
		assertThat(jpaPollingChannelAdapter.getComponentType()).isEqualTo("jpa:inbound-channel-adapter");
	}

}
