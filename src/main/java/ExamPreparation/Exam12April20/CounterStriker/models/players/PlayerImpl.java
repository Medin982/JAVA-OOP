package ExamPreparation.Exam12April20.CounterStriker.models.players;

import ExamPreparation.Exam12April20.CounterStriker.models.guns.Gun;

import static ExamPreparation.Exam12April20.CounterStriker.common.ExceptionMessages.*;

public abstract class PlayerImpl implements Player {

    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    protected PlayerImpl(String userName, int health, int armor, Gun gun) {
        this.setUsername(userName);
        this.setHealth(health);
        this.setArmor(armor);
        this.setGun(gun);
    }

    private void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(INVALID_GUN);
        }
        this.gun = gun;
    }

    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    private void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    private void setUsername(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new NullPointerException(INVALID_PLAYER_NAME);
        }
        this.username = userName;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public void takeDamage(int points) {
        if (this.armor > 0) {
            this.armor -= points;
        } else {
            this.setArmor(0);
            if (this.isAlive()) {
                this.health -= points;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s%n" +
                        "--Health: %d%n" +
                        "--Armor: %d%n" +
                        "--Gun: %s", this.getClass().getSimpleName(), this.getUsername(),
                this.getHealth(), this.getArmor(), this.getGun().getName());
    }
}
