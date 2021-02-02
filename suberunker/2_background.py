import pygame
import os

pygame.init()

# size of screen
screen_width = 480      # screen width size
screen_height = 640     # screen height size
screen = pygame.display.set_mode((screen_width,screen_height))
pygame.display.set_caption("Shooting ball game")  # set the game window name

background = pygame.image.load(os.getcwd() + "/background.png")

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:   # quit the game when click QUIT
            pygame.quit()
            quit()
            print(4)
    # screen.fill((0,0,255))
    screen.blit(background,(0, 0))      # set background
    pygame.display.update()         # update display








