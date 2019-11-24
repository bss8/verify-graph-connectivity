/*
 * Copyright (c) 2019. Borislav S. Sabotinov
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.borislavsabotinov.connectedgraphs.graphs;

/**
 *
 * @param <T>
 */
public class UniDirectionalGraph<T extends Comparable<? super T>> extends BasicGraph<T>  {

    @Override
    public void addEdge(T value1, T value2) {

    }

    @Override
    public void removeEdge(T value1, T value2) {

    }
} // end class UniDirectionalGraph