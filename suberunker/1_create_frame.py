import pygame

pygame.init()


# size of screen
screen_width = 480      # screen width size
screen_height = 640     # screen height size
screen = pygame.display.set_mode((screen_width,screen_height))
pygame.display.set_caption("Shooting ball game")  # set the game window name

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:   # quit the game when click QUIT
            pygame.quit()
            quit()
            print(4)







