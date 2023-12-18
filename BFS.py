from collections import deque


def bfs(graph, start):
    # Initialize the distance ,predecessor,color , and  for each vertex
    distance = {vertex: float('inf') for vertex in graph}
    predecessor = {vertex: None for vertex in graph}
    color = {vertex: 'WHITE' for vertex in graph}

    distance[start] = 0
    color[start] = 'GRAY'
    queue = deque([start])

    while queue:
        u = queue.popleft()

        for v in graph[u]:
            if color[v] == 'WHITE':
                color[v] = 'GRAY'
                distance[v] = distance[u] + 1
                predecessor[v] = u
                queue.append(v)

        color[u] = 'BLACK '  # Mark the vertex u as fully explored .

    return distance, predecessor, color

def print_table(distance ,predecessor,color):
    nodes = sorted(color.keys())
    distances = [distance[node] if distance[node] != float('inf') else "∞" for node in nodes]
    predecessors = [distance[node] if predecessor[node] is not None else "NIL" for node in nodes]
    colors = [color[node] for node in nodes]

    # Print header
    print(f"{'node':^10}{'d':^10}{'π':^10}{'color':^10}")
    print("-" * 40)

    # Print rows
    for node, d, pi, col in zip(nodes, distances, predecessors, colors):
        print(f"{node:^10}{d:^10}{pi:^10}{col:^10}")