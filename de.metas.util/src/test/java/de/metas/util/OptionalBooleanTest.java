package de.metas.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

/*
 * #%L
 * de.metas.util
 * %%
 * Copyright (C) 2019 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

public class OptionalBooleanTest
{
	@Test
	public void only_true_false_and_unknown()
	{
		assertThat(ImmutableSet.copyOf(OptionalBoolean.values()))
				.containsExactly(OptionalBoolean.TRUE, OptionalBoolean.FALSE, OptionalBoolean.UNKNOWN);
	}

	@Test
	public void checkFlags_for_TRUE()
	{
		assertThat(OptionalBoolean.TRUE.isPresent()).isTrue();
		assertThat(OptionalBoolean.TRUE.isUnknown()).isFalse();
		assertThat(OptionalBoolean.TRUE.isTrue()).isTrue();
		assertThat(OptionalBoolean.TRUE.isFalse()).isFalse();
	}

	@Test
	public void checkFlags_for_FALSE()
	{
		assertThat(OptionalBoolean.FALSE.isPresent()).isTrue();
		assertThat(OptionalBoolean.FALSE.isUnknown()).isFalse();
		assertThat(OptionalBoolean.FALSE.isTrue()).isFalse();
		assertThat(OptionalBoolean.FALSE.isFalse()).isTrue();
	}

	@Test
	public void checkFlags_for_UNKNOWN()
	{
		assertThat(OptionalBoolean.UNKNOWN.isPresent()).isFalse();
		assertThat(OptionalBoolean.UNKNOWN.isUnknown()).isTrue();
		assertThat(OptionalBoolean.UNKNOWN.isPresent()).isFalse();
		assertThat(OptionalBoolean.UNKNOWN.isPresent()).isFalse();
	}

	@Test
	public void test_ofBoolean()
	{
		assertThat(OptionalBoolean.ofBoolean(true)).isSameAs(OptionalBoolean.TRUE);
		assertThat(OptionalBoolean.ofBoolean(false)).isSameAs(OptionalBoolean.FALSE);
	}

	@Test
	public void test_ofNullableString()
	{
		assertThat(OptionalBoolean.ofNullableString("Y")).isSameAs(OptionalBoolean.TRUE);
		assertThat(OptionalBoolean.ofNullableString("y")).isSameAs(OptionalBoolean.TRUE);
		assertThat(OptionalBoolean.ofNullableString("true")).isSameAs(OptionalBoolean.TRUE);

		assertThat(OptionalBoolean.ofNullableString("N")).isSameAs(OptionalBoolean.FALSE);
		assertThat(OptionalBoolean.ofNullableString("n")).isSameAs(OptionalBoolean.FALSE);
		assertThat(OptionalBoolean.ofNullableString("false")).isSameAs(OptionalBoolean.FALSE);

		assertThat(OptionalBoolean.ofNullableString("wrongValue")).isSameAs(OptionalBoolean.FALSE);

		assertThat(OptionalBoolean.ofNullableString(null)).isSameAs(OptionalBoolean.UNKNOWN);
		assertThat(OptionalBoolean.ofNullableString("")).isSameAs(OptionalBoolean.UNKNOWN);
		assertThat(OptionalBoolean.ofNullableString("      ")).isSameAs(OptionalBoolean.UNKNOWN);
	}
}
