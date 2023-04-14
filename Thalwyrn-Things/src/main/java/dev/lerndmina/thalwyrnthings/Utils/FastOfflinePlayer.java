package dev.lerndmina.thalwyrnthings.Utils;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.base.Charsets;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class FastOfflinePlayer implements OfflinePlayer
{
    private final String playerName;

    public FastOfflinePlayer(final String playerName) {
        this.playerName = playerName;
    }

    public boolean isOnline() {
        return false;
    }

    public String getName() {
        return this.playerName;
    }

    public UUID getUniqueId() {
        return UUID.nameUUIDFromBytes(this.playerName.getBytes(Charsets.UTF_8));
    }

    @Override
    public @NotNull PlayerProfile getPlayerProfile() {
        return null;
    }

    public boolean isBanned() {
        return false;
    }

    public void setBanned(final boolean banned) {
        throw new UnsupportedOperationException();
    }

    public boolean isWhitelisted() {
        return false;
    }

    public void setWhitelisted(final boolean value) {
        throw new UnsupportedOperationException();
    }

    public Player getPlayer() {
        throw new UnsupportedOperationException();
    }




    public long getFirstPlayed() {
        return System.currentTimeMillis();
    }

    public long getLastPlayed() {
        return System.currentTimeMillis();
    }

    public boolean hasPlayedBefore() {
        return false;
    }

    public Location getBedSpawnLocation() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLastLogin() {
        return 0;
    }

    @Override
    public long getLastSeen() {
        return 0;
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {

    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, int amount) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, int amount) throws IllegalArgumentException {

    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, int newValue) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(@NotNull Statistic statistic) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull Material material) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount) throws IllegalArgumentException {

    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull Material material, int newValue) throws IllegalArgumentException {

    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount) {

    }

    @Override
    public void setStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int newValue) {

    }

    @Override
    public @Nullable Location getLastDeathLocation() {
        return null;
    }

    public boolean isOp() {
        return false;
    }

    public void setOp(final boolean value) {
        throw new UnsupportedOperationException();
    }

    public Map<String, Object> serialize() {
        throw new UnsupportedOperationException();
    }
}