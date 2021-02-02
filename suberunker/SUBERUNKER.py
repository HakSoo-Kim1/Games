import pygame
import random
import os

pygame.init()

# size of screen
screen_width = 480      # screen width size
screen_height = 640     # screen height size
screen = pygame.display.set_mode((screen_width,screen_height))

# set the game window name
pygame.display.set_caption("SUBERUNKER")

# FPS
clock = pygame.time.Clock()

# load background image and caracter image
background = pygame.image.load(os.getcwd() + "/SUBERUNKER_background.jpg")


# character
character = pygame.image.load(os.getcwd() + "/SUBERUNKER_character.jpeg")

# character size
character_size = character.get_rect().size
character_width = character_size[0]
character_height = character_size[1]
character_x_pos = (screen_width / 2) - (character_width / 2)    #set character middle
character_y_pos = screen_height - character_height              # set character bottom
# character movement
to_x = 0    # movement of x
# character movement speed
character_speed = 10

# enemy
enemy = pygame.image.load(os.getcwd() + "/SUBERUNKER_enemy.jpg")
enemy_size = enemy.get_rect().size
enemy_width = enemy_size[0]
enemy_height = enemy_size[1]
enemy_x_pos = random.randint(0, screen_width - enemy_width)
enemy_y_pos = 0
enemy_speed = 10


# running
running = True
while running:
    dt = clock.tick(30) # set frame per second
    # print("fps : " + str(clock.get_fps()))
    for event in pygame.event.get():
        if event.type == pygame.QUIT:   # quit the game when click QUIT
            running = False
        if event.type == pygame.KEYDOWN:    # key pressed
            if event.key == pygame.K_LEFT:
                to_x -= character_speed
            elif event.key == pygame.K_RIGHT:
                to_x += character_speed
        if event.type == pygame.KEYUP:      # key released
            if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                to_x = 0

    character_x_pos += to_x


    if character_x_pos < 0:  # do not cross the left background border
        character_x_pos = 0
    elif character_x_pos > screen_width - character_width:  # do not cross the right background border
        character_x_pos = screen_width - character_width

    enemy_y_pos += enemy_speed

    if enemy_y_pos > screen_height:
        enemy_y_pos = 0
        enemy_x_pos = random.randint(0, screen_width - enemy_width)

    # update rect for collision
    character_rect = character.get_rect()
    character_rect.left = character_x_pos
    character_rect.top = character_y_pos

    enemy_rect = enemy.get_rect()
    enemy_rect.left = enemy_x_pos
    enemy_rect.top = enemy_y_pos

    # check if collision
    if character_rect.colliderect(enemy_rect):
        print("COLLISION!!")
        running = False

    screen.blit(background, (0, 0))  # set background
    screen.blit(character, (character_x_pos, character_y_pos))  # set character
    screen.blit(enemy, (enemy_x_pos, enemy_y_pos))


    pygame.display.update()  # update display
print("DONE")
pygame.time.delay(2000)
pygame.quit()
quit()








