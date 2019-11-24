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

import java.util.List;

/**
 * Interface Graph defines behavior that an implementing class must override.
 * @param <T>
 */
public interface Graph<T extends Comparable<? super T>> {
    void addVertex(T value);
    void removeVertex(T value);
    void addEdge(T value1, T value2);
    void removeEdge(T value1, T value2);
    List<BasicGraph<T>.Vertex> getAdjacentVertices(T value);
}
