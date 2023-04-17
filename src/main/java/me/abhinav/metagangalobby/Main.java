package me.abhinav.metagangalobby;

import me.abhinav.metagangalobby.Commands.Avatar;
import me.abhinav.metagangalobby.Commands.PlayGame;
import me.lagbug.emailer.spigot.api.EmailerAPI;
import net.skinsrestorer.api.PlayerWrapper;
import net.skinsrestorer.api.SkinsRestorerAPI;
import net.skinsrestorer.api.exception.SkinRequestException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public final class Main extends JavaPlugin {
    private static Main main;
    public ArrayList<String> skinsValue;
    public ArrayList<String> skinsSignature;

    private SkinsRestorerAPI skinsRestorerAPI;

    @Override
    public void onEnable() {
        // Plugin startup logic
        main = this;
        Bukkit.getPluginManager().registerEvents(new MainListener(), this);
        Bukkit.getPluginCommand("playgame").setExecutor(new PlayGame());
        Bukkit.getPluginCommand("avatar").setExecutor(new Avatar());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        skinsRestorerAPI = SkinsRestorerAPI.getApi();

        skinsValue = new ArrayList<>();
        skinsValue.add("ewogICJ0aW1lc3RhbXAiIDogMTY4MDM3MTMyMzAyNCwKICAicHJvZmlsZUlkIiA6ICIzYTE5NDgyNTYyZTc0MzFkYmNmOGUwOWE4N2VhMmQ5OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNckxpYW0yNjE0IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdlZWRiZDVmYjY1NzRiZmU2ODZmNWY3ZGM3OGE2ZjMzODIzZWI5OGUwNWRhYWI4MzdmZmJiOGI1MTIyODlmZTUiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==");
        skinsValue.add("ewogICJ0aW1lc3RhbXAiIDogMTY4MDQyNzMwODcyMiwKICAicHJvZmlsZUlkIiA6ICJhNzdkNmQ2YmFjOWE0NzY3YTFhNzU1NjYxOTllYmY5MiIsCiAgInByb2ZpbGVOYW1lIiA6ICIwOEJFRDUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTZmZmYzNGU1NTg1YmUzZWIxNDU2N2IwMzQzYTIwNDJlYzk0NDNjMGQ5OGNlN2FmZmQ5Yzk2MmU5N2JkZDU3MSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9");
        skinsValue.add("ewogICJ0aW1lc3RhbXAiIDogMTY4MDQyOTY1NDA2NywKICAicHJvZmlsZUlkIiA6ICJmNWYyNDcyZGVhNDY0ODJhYWUwNDllYjM2ODE5ODU0NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJEZXRhaWxlcnMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2I4Yjc0ZWZjMTgxNGRmNTIwZjI3MTc2ZjNjZmI5OWQ5NzYxZjQ3OTY3MDE2NDA3NjBhMDBhZWYxOTlkNTJhIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=");
        skinsValue.add("ewogICJ0aW1lc3RhbXAiIDogMTY3MDI1NjU4NTEzOCwKICAicHJvZmlsZUlkIiA6ICJmZTE0M2FhZTVmNGE0YTdiYjM4MzcxM2U1Mjg0YmIxYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJKZWZveHk0IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2MwOTJlMWM5MWRmMDc3N2FiY2ZmYjMwNTE5YzJiNzliMzc0Yzg0YTUzN2NhZGQ3MmJkMWRmZjgwODBjZGIxNmQiCiAgICB9CiAgfQp9");
        skinsValue.add("ewogICJ0aW1lc3RhbXAiIDogMTY4MDM3MDY3NzYwNywKICAicHJvZmlsZUlkIiA6ICJkYmEyNmVkNTk2ZmE0NjBjOTZjOThhYWYwOWM2MDZhNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJPcmlnaW5hbFJlemEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRkODU0NjM1MzJhMjc2OGJjOWI4MmFkZDYyMDkxMzYzYWNlY2FjZDM4NGI3NWNmMGE3ZGQ4MDI3ZjRiODA5ZCIKICAgIH0KICB9Cn0=");
        skinsValue.add("ewogICJ0aW1lc3RhbXAiIDogMTYxOTU3NDcyODkxOCwKICAicHJvZmlsZUlkIiA6ICJjNjc3MGJjZWMzZjE0ODA3ODc4MTU0NWRhMGFmMDI1NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJDVUNGTDE2IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2JiNDE0N2QzMjcwODY1OThhYWU4MTIwNDdlZjBkMzdjNmRiYzhjMmY5ZDg1ZTRiODE3OTE4Zjc4MzQzOTE0ZTYiCiAgICB9CiAgfQp9");

        skinsSignature = new ArrayList<>();
        skinsSignature.add("G0FpKE66ml55pXwZ7fV7Mnw4LClJIpxO5XnHUt/p7cwtBELIDj20HsvgmKh9Rz/s9FNmCHMN38glPcFmXeODY2ZVIERW+HfLJJFOIjo5IEJ3HZLWF/pcPeXJIwSG4gin1ThBxXfjIJQWLz4IHikHw4bg07YjcFyGj3NeV/DmGXHSQDlrDlsPyq5ZxZA0YPuDpS+BdYwK46Br63W+1Mj1E/knMjbvBqzg2z0UO5cyGoUPcZ/OIfethRgoz3mxQufQOPKkMSkvURt1DNFxvgUfVPVrciGTHDrMLdkKeIRBzH+umHK/Fk8Rqj1KYPsSkCqfNq7JGn8JLkwgCR4QzsrgAMaD8ZNOZCrfLrXqUMTTDUDts34/epMWzjtZ/TKdTwmek83d4iAnYwjpNEZ4Mo+i5hrLi4I2yvSjhWPp7kHmg8zOezonGZ+L1mgnd4yitUheyVEpmF17AGTqKr6pxWJcj0ZDPCtW7gcEFeSgE5dZazwATz4vGtnnfWW92HchXVgQjwzSwwiVm73uraAWWZDCfS0Wyu9clfa+RNqOV940RkgoWmFypFiKLfZzvcIwEuXA2pVu8nrXIe+lueO/6posaQyPTIPYbD9TwJCSWiUw2WXdee19i26qa8SQgdAR2/xlHAhCKJlFy72Rg1PuvPAz9ox5iRfvgXWVYDgW2i7fWCA=");
        skinsSignature.add("rxUcb0wryAM8lN0Xo9KlpcODr9DUIX/2Bj4aojuE3OMLTco370pa1e4muaiOrzB0LmTsmRIjgXqwXoS4AZ1OZaBEoV6PE5ZKAIR34vKhUix2MFMVsW25H5OQyimOAFM1r/clrJKutpOZoq+x/BDP7SGAtPvTZg/geeVHJcot9QZGUTYCd7fVT7Fz/1298iahNAdOumRjmaiHe5rdDEKvUc2/gjaqCwZSUr6eYWeUY5VoZTFD2UIXEo6CvI6pOtresRPY11Gq6s95ok5aFPcZQkvpHzxlYbXLyUCth+3/jO3Wwzj1swWjPpCpLQvbr9mMc8YkMdtQt4FrPVy/6lxZeGZ9vMDEehnudoxpeMQaptvDR6CIZmT+uKTe/5a0RULi7MfJrx2owtiiAswt1bCJmw6T/bW2axb1XPbKLEbJTU1zrilWkaXi6xr7EA0howcBybFNfVYZkcSdWcnQ5WvZbXBHJK5rBSI+yHaLxkhpTUu/mdPXy5SAmMT1UNONMOlgr3D08IxTuO601dtUol4gC1FCCkP7NqHpGKwYoziUGVj13Ja2xLk5q+H6+6AvfqMvXKd8YaginIaghfgYlPyB5aQfFssWzpsOzzLTONVoUKfKYBNBatAbG6fa3MXpTyG5+jLjUmaANMwmMZG4WGhHdpW11idOgEav0nN9CNGA6kU=");
        skinsSignature.add("MOless1pn9X7qyvhWc7gUgqsbvhyfVozL3WMc+VMihUhK0wJ4KGjWjFs9WksM1AZfC3m52A883IbpCNQxS0GWVyilJte/ZwzdKnKhwxzRRlxbmXJQaZfjPGTeC6EXueKb6My+5zJ7Dw1HnFyGXh4sucseJfwaAHaQP/wuha6hOC57FqT2MboLPkor/5YnlqIMWvAiOUmPAISoNNk3UPgtStD5hT405SuErl+bCZ8hHl8o/LgrdP2FoWapDLS1hkR+sbOWaXfaSzk0dM7XEushzmhgTI5XO4hrQzSxgpZ6YTJMiCS+BHO4fhdNIZdgj2Ad7rPolQKtNJaVDZl5z8X7bT88fMk0Y+TOhlDSfj9Odbd0fiJhSkytfB9x4RhEw7HbSmeqmavqro36+tc+ynV46CDoGq7JfvzS/KPw9BHLBhX+F2daU1h9d0GDiwKNOSMq4xGblzaIt99UUDohUxbKKbi33VMD5lXoiPVis0qHJLDvyfee1jRxVFi6is5her+3p0ENbAe2mHQcyDxq/ol67jItOtl1HkQpIGDPHp6GKgIhIdGgzrbcRL+XpjXRSiYiKD9rBqUpSL3Q/392BQz+DNy9sJmMQs9jAaVmOhAcrrxKvso7J1xmiOjR0pTVvk/WQ/vyAOH75s/RF5i17y5THTh9FaadBEUQ2vf4gUGn78=");
        skinsSignature.add("xHxzB2iqDeFKtAkJ4W6cbM3Joy+Zrz8OlFpPsgBVDqpv4NLR46uOZqrSuRijGBHEMbW+IUavkBPMNsjXIqBbvKCKluuhS+ureHmlx11t2k9w4n4tEdFD6PNwxFWZqmI3nmOQrqFjOOXwR7XT6iLBEfHPGlHEc6tG29DTRvfwC6y8QtbnuAV720OLUCEa/QPhK2LWUW5bEfKcXGrTPWpOjySgh//Y5sA4Wi9dGJvd8TCGsj3rgFHprAchPnd+z0tU3rWZHlXrF7iyP9cBe0LCGg1hxAUG3Yhd6BMWOlGJRD/QJ1mXq9QYnn0SV1v2SABRZc/5ZbLCv86unZ2S35DWNt92+3gJvD/Dm8ZqA3V2A/QQwyr+9lFrWt5rq5nWT9jLDh7xAL1zBGZUuwES2dRcloPh0C1otyT+gl0oeYIi+5Y06sgFZ9QE/mNdbWgcgdpQZEJEqWylOdepWHajcKEpYunj7oZrNxsJtmwqQoPRXCFLE2UZgSHVRzCpCGC25gwk7R2uyZMhKHTT0gAJ/GZ9ID3N+owYpNr7WP69LKm93m6wapXZfrmomUS1fDeRYy4fbYBUsp+h/6pYRcyQM1mRMl8L7WzRm3BEHF3IU/6VPTZraqpx6YXdkiR9ySI1Xj3NhTvV+utOd0nwB9wwYV1dErwxWmywCKJQR4N+qZPOClU=");
        skinsSignature.add("sn86dV9/RQqosNl26XMt1ZlBkUnAz7/ekY0XyI6NXnXWZHrXEDc4GPwxqOiOdmJBmU8mGWw8cejWqRB7o5EUhPwoX+1t8fORPFoYjWZwuvdBuW31UpCeipVedUe8r9lq7Ef4CnMw5U5uplbO+dEZbazbhkL8L1sfLmeMzLy5AwicGFYGyh2p10C5wIsXzVDEyXjQFnHqfh3iLwDqEh+CjxoqrplHYk2lxkkPRFwjyM24hEeZ2wWQI2LlNM5n6TECHGfAuH2vcpTmlsA90YBeKn8ftjwDhQcCk08u+prbJyYBslFc6o1mcctKJkhCaXE/d2fn9H4+xDEc3kncK/WXYDr8xVdP+nR2yEiR2ecKLFZuxbqIoQ+FaBErBXJvmsIS9G8oOD1ZnvYG0+4BepyazBj1gQcsmZm7p+9HI2Il6PSFLF4RMIr7qNiUP6iiy2pQlTUCpKELkPC8Q4EwJEddDrzdpC+eMoGp/cLBZT4ztWfdY1S65HvJaOLY9gmzPdy+arkWrc+HCUGfLIqBxWbhlMaFuOtiJ9r+1CqvqKBXYeL/iJyq13GGo5pWYC+JW2MV5ujDbGlEkNHAj8vOPsn2MF3VBox59yLUq3mKadVyF2meSRRei8D0GulVgN3pRYcU0Xvf/rBaiRXeM8CWM8USCNpHf97WqljfezQyGv6CpE8=");
        skinsSignature.add("fkw2bmnSnRfrIGuBICkZbtTxpZwDaByf6tlSDNXZqkjHCAjCCAG4X7MJlBXl2zBF4xqNIAJDK8cT69Ts3g3qiLtFnxB8AYoOteXgwU9bfyOz8xnPSP2hD5a5mH8uXmd/DTx6VJxjwHaRBoMfC9vtHsiP+kGHrwjFNPG0z5SjknzCftZquqFU/342lVdhlpbVb35+KGmOtpfA+VfnJTAypjvVhHsyJqM9Pp0xUQH8LzsBBlpK+8pxAHcw5nwgw9aKwm3TIHqSNaj57K5Y04MkYeidA/pSFIAnKcAAfAnmOTczZeDirXW2AYNLs9AjqKbHzefXFWrDVWvw4QCd3SH+1ijeUW7ebFh3NQsPCtfWJ9+1wpcTbIak0KSUarEoZ3xCFaT6UufOKQljF++LumRbTP4OdEGyO43Gze8PjD5HpZYTCza+6C2MTSWOAkigaZsbC7wCIptvvSpT97jcKyMibA/5MYKFOe8iOCO0MNuWfxVKntO9y+gFsMkXNHm9H30u+jjn2GWkK3Vj9o78ykfeBZABi3egHEwkq22ns8OFKqFTW4ACTj/qW5qLfcNRBKRCvHoB9l5vGhrZdgeZl61PNjwJdimrc39qeiujWPG4GX+PZO3EUoNNcngWd8GZLrENc6ixgc3UVcUFIpTwoswF8Rh5InbBqOUZLG4HPiw2aWg=");

        EmailerAPI api = new EmailerAPI();
        remindEmail();
    }

    void remindEmail() {
        new BukkitRunnable(){
            @Override
            public void run() {
                for(Player p: Bukkit.getOnlinePlayers()) {
                    if(!p.hasPermission("email.verified")) {
                        p.sendMessage(ChatColor.RED + "Please connect your Email to play!");
                        p.sendMessage(ChatColor.GREEN + "Use - /setemail [email-address]");
                        p.playSound(p, Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                    }
                }
            }
        }.runTaskTimer(main, 1200L, 1200L);
    }

    public void applySkin(Player player, int skin) {
        String name = "";
        String value = skinsValue.get(skin-1);
        String signature = skinsSignature.get(skin-1);
        switch (skin) {
            case 1:
                name = "girl1";
                break;
            case 2:
                name = "girl2";
                break;
            case 3:
                name = "girl3";
                break;
            case 4:
                name = "boy1";
                break;
            case 5:
                name = "boy2";
                break;
            case 6:
                name = "boy3";
                break;
            default:
                return;
        }

        try {
            skinsRestorerAPI.setSkinData(name, skinsRestorerAPI.createPlatformProperty("textures", value, signature), 0);
            skinsRestorerAPI.setSkin(player.getName(), name);
            skinsRestorerAPI.applySkin(new PlayerWrapper(player));
        } catch(SkinRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return main;
    }
}
