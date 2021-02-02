import pygame
import os

pygame.init()

# size of screen
screen_width = 480      # screen width size
screen_height = 640     # screen height size
screen = pygame.display.set_mode((screen_width,screen_height))
pygame.display.set_caption("Shooting ball game")  # set the game window name

background = pygame.image.load(os.getcwd() + "/background.png")
character = pygame.image.load(os.getcwd() + "/character.png")

# character = pygame.image.load("/Users/haksoo/Documents/GitHub/suberunker/character.png") # load character image

character_size = character.get_rect().size  # character size
character_width = character_size[0]
character_height = character_size[1]
character_x_pos = (screen_width / 2) - (character_width / 2)    #set character middle
character_y_pos = screen_height - character_height              # set character bottom

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:   # quit the game when click QUIT
            pygame.quit()
            quit()
            print(4)
    # screen.fill((0,0,255))
    screen.blit(background,(0, 0))      # set background
    screen.blit(character,(character_x_pos,character_y_pos))    # set character

    pygame.display.update()         # update display



