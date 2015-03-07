package com.serchinastico.mechrunner.schedule.ui.action;

/*
 * Copyright (C) 2015 Sergio Gutiérrez Mota.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergio Gutiérrez Mota.
 */
public class ViewsList extends ArrayList<View> {

	private static final long serialVersionUID = -4426511682928240685L;

	public Pair<View, String>[] toPairsArray() {
		List<Pair<View, String>> views = new ArrayList<>();

		for (View view : this) {
			views.add(new Pair<>(view, view.getTransitionName()));
		}

		return (Pair<View, String>[]) views.toArray(new Pair[0]);
	}

}
