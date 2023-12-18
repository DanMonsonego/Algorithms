from BFS import bfs,print_table


def main():
    graph = {
        'S': ['A', 'B', 'C'],
        'A': ['B', 'D'],
        'B': ['C', 'D'],
        'C': ['D', 'E'],
        'D': ['F', 'G'],
        'E': ['D', 'G'],
        'F': ['A'],
        'G': ['F']
    }
    distance, predecessor, color = bfs(graph, 'S')
    print_table(distance,predecessor,color)



if __name__ == '__main__':
    main()
