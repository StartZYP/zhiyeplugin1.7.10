package rpg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.text.DecimalFormat;
import java.util.*;

public class States implements Listener, CommandExecutor {
    public static Map<String, Attr> playerAttr = new HashMap();
    public static Map<String, Long> damagespeed = new HashMap();
    public static Map<String, Boolean> isFirstDamage = new HashMap();
    public static Map<String, Integer> playerZDL = new HashMap();



    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("chu ");
            return false;
        } else if (args.length == 2) {
            return false;
        } else if (args.length == 1 && args[0].equals("zdl")) {
//            Attr attr = this.getPlayerAttr((Player)sender);
//            playerAttr.put(sender.getName(), attr);
            Map<String, Integer> zdl = sortByValue(playerZDL);
            sender.sendMessage("§c§l幻战域排行榜");
            int i;
            Iterator i$;
            Map.Entry s;
            if (zdl.size() < 10) {
                i = 1;

                for (i$ = zdl.entrySet().iterator(); i$.hasNext(); ++i) {
                    s = (Map.Entry) i$.next();
                    sender.sendMessage("§6§l第" + i + "名:" + (String) s.getKey() + "战斗力" + s.getValue());
                }
            } else {
                i = 1;
                i$ = zdl.entrySet().iterator();

                while (i$.hasNext()) {
                    s = (Map.Entry) i$.next();
                    sender.sendMessage("§6§l第" + i + "名:" + (String) s.getKey() + "战斗力" + s.getValue());
                    ++i;
                    if (i == 11) {
                        break;
                    }
                }
            }

            sender.sendMessage("§6§l你的战斗力" + playerZDL.get(sender.getName()));
            return false;
        }
        return false;
    }


    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {
        List<Map.Entry<String, Integer>> list = new LinkedList(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return ((Integer) o2.getValue()).compareTo((Integer) o1.getValue());
            }
        });
        Map<String, Integer> sortedMap = new LinkedHashMap();
        Iterator i$ = list.iterator();

        while (i$.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry) i$.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof Player))return;
        Player player= (Player) event.getDamager();
        if(damagespeed.get(player.getName())==0||damagespeed.get(player.getName())==null){
            damagespeed.put(player.getName(),Calendar.getInstance().getTimeInMillis());
        }else {
            long last=damagespeed.get(player.getName());
            if(Calendar.getInstance().getTimeInMillis()-last<1.0d/playerAttr.get(player.getName()).getDamagespeed()) {
                event.setCancelled(true);
                player.sendMessage("你处于攻击冷却中");
                return;
            }else {
                damagespeed.put(player.getName(),Calendar.getInstance().getTimeInMillis());
            }
        }
        Attr attr1=playerAttr.get(player.getName());
        double crit=attr1.getBaoji();
        double critdamage=1;
        if(Math.random()<crit/100){
            critdamage+=attr1.getBaojidamage()/100.0d;
        }
        if(event.getEntity() instanceof Player){
            Attr attr=playerAttr.get(((Player) event.getEntity()).getName());
            event.setDamage((attr1.getDamage()-attr.getDenfense())*critdamage);
        }else {
            event.setDamage(attr1.getDamage()*critdamage);
        }
    }



    @EventHandler
    public void onRegion(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setAmount(1.0D);
            double he = event.getRegainReason().ordinal();
            Player player = (Player) event.getEntity();
            player.sendMessage("§c§l[RPG系统]§e§l你已回复生命值" + he + "点");
        }

    }

    public String getValueOf(double value) {
        DecimalFormat df = new DecimalFormat("#.00");
        String str = df.format(value);
        return str;
    }

    public void setSpeed(Attr attr, Player player) {
        int speed = attr.getMovespeed();
        if (speed > 1) {
            --speed;
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 9999999, speed);
            player.addPotionEffect(potionEffect);
        } else if (player.hasPotionEffect(PotionEffectType.SPEED)) {
            player.removePotionEffect(PotionEffectType.SPEED);
        }

    }

    public void setPlayerAttr(Attr attr, Player player) {
        player.setMaxHealth(attr.getHealth() + 20.0D);
    }

    public boolean isFirstDamage(Player player) {
        return (Boolean) isFirstDamage.get(player.getName());
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        damagespeed.put(player.getName(), 0L);
        isFirstDamage.put(player.getName(), true);
        Attr attr = this.getPlayerAttr(player);
        playerAttr.put(player.getName(), attr);
        this.setSpeed(attr, player);
        this.setPlayerAttr(attr, player);
        this.setPlayerzdl(attr, player);
    }

    public int setPlayerzdl(Attr attr, Player player) {
        int his = 0;
        if (playerZDL.get(player.getName()) != null) {
            his = (Integer) playerZDL.get(player.getName());
        }

        int now = attr.getZdl();
        if (his <= now) {
            playerZDL.put(player.getName(), now);
            return now;
        } else {
            return his;
        }
    }

    @EventHandler
    public void onchangeweapon(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int i = event.getNewSlot();
        System.out.println("新孔" + i);
        Attr attr = this.getZdl(player, i);
        playerAttr.put(player.getName(), attr);
        this.setSpeed(attr, player);
        this.setPlayerAttr(attr, player);
        this.setPlayerzdl(attr, player);
        System.out.println(attr.getZdl());
    }

    public Attr getZdl(Player player, int i) {
        Attr result = new Attr(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1, 0,0,0);
        TempAttr newattr = null;
        if (player.getInventory().getItem(i) != null) {
            newattr = this.getEquipStates(player.getInventory().getItem(i));
            System.out.println("手上的武器" + player.getInventory().getItem(i));
            result.setAttr(newattr);
            result = result.getAllAttr();
        }

        ItemStack[] itemStacks = player.getInventory().getArmorContents();
        ItemStack[] arr$ = itemStacks;
        int len$ = itemStacks.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            ItemStack itemStack = arr$[i$];
            if (itemStack != null) {
                TempAttr attr = this.getEquipStates(itemStack);
                if (attr != null) {
                    System.out.println(attr);
                    result.setAttr(attr);
                    result = result.getAllAttr();
                }
            }
        }

        return result;
    }

    @EventHandler
    public void dropEvent(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Attr attr = this.getPlayerAttr(player);
        playerAttr.put(player.getName(), attr);
        this.setSpeed(attr, player);
        this.setPlayerAttr(attr, player);
        this.setPlayerzdl(attr, player);
    }

    @EventHandler
    public void breakEvent(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();
        Attr attr = this.getPlayerAttr(player);
        playerAttr.put(player.getName(), attr);
        this.setSpeed(attr, player);
        this.setPlayerAttr(attr, player);
        this.setPlayerzdl(attr, player);
    }

    @EventHandler
    public void closeInv(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Attr attr = this.getPlayerAttr(player);
        playerAttr.put(player.getName(), attr);
        this.setSpeed(attr, player);
        this.setPlayerAttr(attr, player);
        this.setPlayerzdl(attr, player);
    }

    public Attr getPlayerAttr(Player player) {
        Attr result = new Attr(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1, 0,0,0);
        TempAttr newattr = null;
        if (player.getItemInHand() != null) {
            newattr = this.getEquipStates(player.getItemInHand());
            //System.out.println("手上的武器" + player.getItemInHand());
            result.setAttr(newattr);
            result = result.getAllAttr();
            System.out.println(result);
        }

        ItemStack[] itemStacks = player.getInventory().getArmorContents();
        ItemStack[] arr$ = itemStacks;
        int len$ = itemStacks.length;
        for (int i$ = 0; i$ < len$; ++i$) {
            ItemStack itemStack = arr$[i$];
            if (itemStack != null) {
                TempAttr attr = this.getEquipStates(itemStack);
                if (attr != null) {
                    System.out.println(attr);
                    result.setAttr(attr);
                    result = result.getAllAttr();
                }
            }
        }

        return result;
    }

    public TempAttr getEquipStates(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) {
            return null;
        } else if (itemStack.getItemMeta().getLore() == null) {
            return null;
        } else if (itemStack.getItemMeta().getLore().size() == 0) {
            return null;
        } else {
            double damage = 0.0D;
            double denfense = 0.0D;
            double magic = 0.0D;
            double damagespeed = 0.0D;
            double health = 0.0D;
            int movespeed = 0;
            int zdl = 0;
            int baoji=0;
            int baojidamage=0;

            String lore;
            for (Iterator i$ = itemStack.getItemMeta().getLore().iterator(); i$.hasNext(); zdl = (int) ((double) zdl + (double) this.getZDL(lore) + 100.0D * damage + 50.0D * health + 80.0D * magic)) {
                lore = (String) i$.next();
                damage += this.getDamage(lore);
                denfense += this.getDenfense(lore);
                magic += this.getMagic(lore);
                damagespeed += (double) this.getDamagespeed(lore);
                health += this.getHealth(lore);
                movespeed += this.getMovespeed(lore);
                baoji +=this.getBaoji(lore);
                baojidamage +=this.getBaojidamage(lore);
            }

            System.out.println("战斗力" + zdl);

            return new TempAttr(damage, denfense, magic, damagespeed, health, movespeed, zdl,baoji,baojidamage);
        }
    }

    public int getZDL(String lore) {
        if (lore.contains(Config.zdl)) {
            String[] s = lore.split(":");
            return Integer.parseInt(s[1]);
        } else {
            return 0;
        }
    }

    public double getDamage(String lore) {
        if (lore.contains(Config.damage)) {
            String[] s = lore.split(":");
            return Double.parseDouble(s[1]);
        } else {
            return 0.0D;
        }
    }

    public double getDenfense(String lore) {
        if (lore.contains(Config.denfense)) {
            String[] s = lore.split(":");
            return Double.parseDouble(s[1]);
        } else {
            return 0.0D;
        }
    }

    public double getMagic(String lore) {
        if (lore.contains(Config.magic)) {
            String[] s = lore.split(":");
            return Double.parseDouble(s[1]);
        } else {
            return 0.0D;
        }
    }

    public int getDamagespeed(String lore) {
        if (lore.contains(Config.damagespeed)) {
            String[] s = lore.split(":");
            return Integer.parseInt(s[1]);
        } else {
            return 0;
        }
    }

    public double getHealth(String lore) {
        if (lore.contains(Config.health)) {
            String[] s = lore.split(":");
            return (double) Integer.parseInt(s[1]);
        } else {
            return 0.0D;
        }
    }

    public int getMovespeed(String lore) {
        if (lore.contains(Config.movespeed)) {
            String[] s = lore.split(":");
            return Integer.parseInt(s[1]);
        } else {
            return 0;
        }
    }
    public int getBaoji(String lore) {
        if (lore.contains(Config.baoji)) {
            String[] s = lore.split(":");
            return Integer.parseInt(s[1].replace("%",""));
        } else {
            return 0;
        }
    }

    public int getBaojidamage(String lore) {
        if (lore.contains(Config.baojidamage)) {
            String[] s = lore.split(":");
            return Integer.parseInt(s[1].replace("%",""));
        } else {
            return 0;
        }
    }


}