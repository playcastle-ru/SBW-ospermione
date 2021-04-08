package org.screamingsandals.bedwars.placeholderapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.screamingsandals.bedwars.Main;
import org.screamingsandals.bedwars.api.game.GameStatus;
import org.screamingsandals.bedwars.game.Game;
import org.screamingsandals.bedwars.game.GameManager;
import org.screamingsandals.bedwars.player.PlayerManager;
import org.screamingsandals.bedwars.statistics.PlayerStatisticManager;
import org.screamingsandals.lib.player.PlayerMapper;

public class BedwarsExpansion extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return String.join(", ", Main.getInstance().getPluginDescription().getAuthors());
    }

    @Override
    public String getIdentifier() {
        return "bedwars";
    }

    @Override
    public String getVersion() {
        return Main.getVersion();
    }

    @Override
    public String getPlugin() {
        return Main.getInstance().getPluginDescription().getName();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        // any game
        if (identifier.startsWith("game_")) {
            var gameName = identifier.substring(5);
            var index = gameName.lastIndexOf("_");
            var operation = gameName.substring(index + 1).toLowerCase();
            if (operation.equals("teams")) {
                index = gameName.lastIndexOf("_", index - 1);
                operation = gameName.substring(index + 1).toLowerCase();
            }
            gameName = gameName.substring(0, index);
            var gameOpt = GameManager.getInstance().getGame(gameName);
            if (gameOpt.isPresent()) {
                var game = gameOpt.get();
                switch (operation) {
                    case "name":
                        return game.getName();
                    case "players":
                        return Integer.toString(game.countConnectedPlayers());
                    case "maxplayers":
                        return Integer.toString(game.getMaxPlayers());
                    case "minplayers":
                        return Integer.toString(game.getMinPlayers());
                    case "world":
                        return game.getWorld().getName();
                    case "state":
                        return game.getStatus().name().toLowerCase();
                    case "available_teams":
                        return Integer.toString(game.countAvailableTeams());
                    case "connected_teams":
                        return Integer.toString(game.countRunningTeams());
                    case "teamchests":
                        return Integer.toString(game.countTeamChests());
                }
            }
        }

        // other player stats

        if (identifier.startsWith("otherstats_")) {
            if (!PlayerStatisticManager.isEnabled()) {
                return null;
            }
            var playerName = identifier.substring(11);
            var index = playerName.lastIndexOf("_");
            var operation = playerName.substring(index + 1).toLowerCase();
            if (operation.equals("beds")) {
                index = playerName.lastIndexOf("_", index - 1);
                operation = playerName.substring(index + 1).toLowerCase();
            }
            playerName = playerName.substring(0, index);

            var stats = PlayerStatisticManager.getInstance().getStatistic(
                    PlayerMapper.wrapOfflinePlayer(Bukkit.getOfflinePlayer(playerName))
            );

            if (stats == null) {
                return null;
            }

            switch (operation) {
                case "deaths":
                    return Integer.toString(stats.getDeaths());
                case "destroyed_beds":
                    return Integer.toString(stats.getDestroyedBeds());
                case "kills":
                    return Integer.toString(stats.getKills());
                case "loses":
                    return Integer.toString(stats.getLoses());
                case "score":
                    return Integer.toString(stats.getScore());
                case "wins":
                    return Integer.toString(stats.getWins());
                case "games":
                    return Integer.toString(stats.getGames());
                case "kd":
                    return Double.toString(stats.getKD());
            }
        }

        // Player
        if (player == null) {
            return "";
        }

        if (identifier.startsWith("current_")) {
            // current game
            switch (identifier.toLowerCase().substring(8)) {
                case "game":
                    return PlayerManager.getInstance().getGameOfPlayer(player.getUniqueId()).map(Game::getName).orElse("none");
                case "game_players":
                    return PlayerManager.getInstance().getGameOfPlayer(player.getUniqueId()).map(g -> Integer.toString(g.countConnectedPlayers())).orElse("0");
                case "game_time":
                    var m_Game = PlayerManager.getInstance().getGameOfPlayer(player.getUniqueId());
                    if (m_Game.isEmpty() || m_Game.get().getStatus() != GameStatus.RUNNING)
                        return "0";
                    return m_Game.get().getFormattedTimeLeft();
                case "game_maxplayers":
                    return PlayerManager.getInstance().getGameOfPlayer(player.getUniqueId()).map(g -> Integer.toString(g.getMaxPlayers())).orElse("0");
                case "game_minplayers":
                    return PlayerManager.getInstance().getGameOfPlayer(player.getUniqueId()).map(g -> Integer.toString(g.getMinPlayers())).orElse("0");
                case "game_world":
                    return PlayerManager.getInstance().getGameOfPlayer(player.getUniqueId()).map(g -> g.getWorld().getName()).orElse("none");
                case "game_state":
                    return PlayerManager.getInstance().getGameOfPlayer(player.getUniqueId()).map(g -> g.getStatus().name().toLowerCase()).orElse("none");
                case "available_teams":
                    return PlayerManager.getInstance().getGameOfPlayer(player.getUniqueId()).map(g -> Integer.toString(g.countAvailableTeams())).orElse("0");
                case "connected_teams":
                    return PlayerManager.getInstance().getGameOfPlayer(player.getUniqueId()).map(g -> Integer.toString(g.countRunningTeams())).orElse("0");
                case "teamchests":
                    return PlayerManager.getInstance().getGameOfPlayer(player.getUniqueId()).map(g -> Integer.toString(g.countTeamChests())).orElse("0");
                case "team":
                    if (PlayerManager.getInstance().isPlayerInGame(player.getUniqueId())) {
                        var gPlayer = PlayerManager.getInstance().getPlayer(player.getUniqueId()).get();
                        var game = gPlayer.getGame();
                        if (gPlayer.isSpectator) {
                            return "spectator";
                        } else {
                            var team = game.getPlayerTeam(gPlayer);
                            if (team != null) {
                                return team.getName();
                            } else {
                                return "none";
                            }
                        }
                    } else {
                        return "none";
                    }
                case "team_colored":
                    if (PlayerManager.getInstance().isPlayerInGame(player.getUniqueId())) {
                        var gPlayer = PlayerManager.getInstance().getPlayer(player.getUniqueId()).get();
                        var game = gPlayer.getGame();
                        if (gPlayer.isSpectator) {
                            return ChatColor.GRAY + "spectator";
                        } else {
                            var team = game.getPlayerTeam(gPlayer);
                            if (team != null) {
                                return team.teamInfo.color.chatColor + team.getName();
                            } else {
                                return ChatColor.RED + "none";
                            }
                        }
                    } else {
                        return ChatColor.RED + "none";
                    }
                case "team_color":
                    if (PlayerManager.getInstance().isPlayerInGame(player.getUniqueId())) {
                        var gPlayer = PlayerManager.getInstance().getPlayer(player.getUniqueId()).get();
                        var game = gPlayer.getGame();
                        if (gPlayer.isSpectator) {
                            return ChatColor.GRAY.toString();
                        } else {
                            var team = game.getPlayerTeam(gPlayer);
                            if (team != null) {
                                return team.teamInfo.color.chatColor.toString();
                            } else {
                                return ChatColor.GRAY.toString();
                            }
                        }
                    } else {
                        return "";
                    }
                case "team_players":
                    if (PlayerManager.getInstance().isPlayerInGame(player.getUniqueId())) {
                        var gPlayer = PlayerManager.getInstance().getPlayer(player.getUniqueId()).get();
                        var game = gPlayer.getGame();
                        if (gPlayer.isSpectator) {
                            return "0";
                        } else {
                            var team = game.getPlayerTeam(gPlayer);
                            if (team != null) {
                                return Integer.toString(team.countConnectedPlayers());
                            } else {
                                return "0";
                            }
                        }
                    } else {
                        return "0";
                    }
                case "team_maxplayers":
                    if (PlayerManager.getInstance().isPlayerInGame(player.getUniqueId())) {
                        var gPlayer = PlayerManager.getInstance().getPlayer(player.getUniqueId()).get();
                        var game = gPlayer.getGame();
                        if (gPlayer.isSpectator) {
                            return "0";
                        } else {
                            var team = game.getPlayerTeam(gPlayer);
                            if (team != null) {
                                return Integer.toString(team.getMaxPlayers());
                            } else {
                                return "0";
                            }
                        }
                    } else {
                        return "0";
                    }
                case "team_bed":
                    if (PlayerManager.getInstance().isPlayerInGame(player.getUniqueId())) {
                        var gPlayer = PlayerManager.getInstance().getPlayer(player.getUniqueId()).get();
                        var game = gPlayer.getGame();
                        if (gPlayer.isSpectator) {
                            return "no";
                        } else {
                            var team = game.getPlayerTeam(gPlayer);
                            if (team != null) {
                                return team.isBed ? "yes" : "no";
                            } else {
                                return "no";
                            }
                        }
                    } else {
                        return "no";
                    }
                case "team_teamchests":
                    if (PlayerManager.getInstance().isPlayerInGame(player.getUniqueId())) {
                        var gPlayer = PlayerManager.getInstance().getPlayer(player.getUniqueId()).get();
                        var game = gPlayer.getGame();
                        if (gPlayer.isSpectator) {
                            return "0";
                        } else {
                            var team = game.getPlayerTeam(gPlayer);
                            if (team != null) {
                                return Integer.toString(team.countTeamChests());
                            } else {
                                return "0";
                            }
                        }
                    } else {
                        return "0";
                    }
            }
        }

        // Stats

        if (identifier.startsWith("stats_")) {
            if (!PlayerStatisticManager.isEnabled()) {
                return null;
            }

            var stats = PlayerStatisticManager.getInstance().getStatistic(PlayerMapper.wrapPlayer(player));

            if (stats == null) {
                return null;
            }

            switch (identifier.toLowerCase().substring(6)) {
                case "deaths":
                    return Integer.toString(stats.getDeaths());
                case "destroyed_beds":
                    return Integer.toString(stats.getDestroyedBeds());
                case "kills":
                    return Integer.toString(stats.getKills());
                case "loses":
                    return Integer.toString(stats.getLoses());
                case "score":
                    return Integer.toString(stats.getScore());
                case "wins":
                    return Integer.toString(stats.getWins());
                case "games":
                    return Integer.toString(stats.getGames());
                case "kd":
                    return Double.toString(stats.getKD());
            }
        }

        return null;
    }

}
