package mcjty.lostcities.gui;

import com.google.gson.JsonParser;
import mcjty.lostcities.config.LostCityConfiguration;
import mcjty.lostcities.config.LostCityProfile;
import net.minecraft.client.gui.screen.CreateWorldScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiLostCityConfiguration extends Screen {

    private final CreateWorldScreen parent;
    private Map<Integer, Runnable> actionHandler = new HashMap<>();
    private Map<Integer, String> profileNames = new HashMap<>();
    private int page = 0;
    private int numpages;
//    private GuiMutableLabel pagelabel;

    public GuiLostCityConfiguration(CreateWorldScreen parent) {
        super(new StringTextComponent("Lost City Configuration"));
        this.parent = parent;
    }

    @Override
    public void init() {
        JsonParser parser = new JsonParser();
        String profileName = LostCityConfiguration.DEFAULT_PROFILE;
        // @todo 1.14
//        if (parent.chunkProviderSettingsJson != null && !parent.chunkProviderSettingsJson.trim().isEmpty()) {
//            JsonElement parsed = parser.parse(parent.chunkProviderSettingsJson);
//            if (parsed.getAsJsonObject().has("profile")) {
//                profileName = parsed.getAsJsonObject().get("profile").getAsString();
//            }
//        }

        page = 0;
        numpages = (countPublicProfiles() + 7) / 8;

        setupGui(profileName);
    }

    private int countPublicProfiles() {
        int cnt = 0;
        for (Map.Entry<String, LostCityProfile> entry : LostCityConfiguration.profiles.entrySet()) {
            if (entry.getValue().isPublic()) {
                cnt++;
            }
        }
        return cnt;
    }

    private void setupGui(String profileName) {
        actionHandler.clear();
        profileNames.clear();
        this.buttons.clear();
        int id = 301;
        int y = 8;
        int num = -1;
        int cnt = 0;

        List<String> profileKeys = new ArrayList<>(LostCityConfiguration.profiles.keySet());
        profileKeys.sort(String::compareTo);
        for (String key : profileKeys) {
            LostCityProfile profile = LostCityConfiguration.profiles.get(key);
            if (profile.isPublic()) {
                num++;
                if (num < page * 8) {
                    continue;
                }
                if (cnt >= 8) {
                    break;
                }
                cnt++;
                Button button = new Button(10, y, 90, 20, key, p -> {});
                if (profileName.equals(profile.getName())) {
                    button.setFGColor(0xffffff00);
                }
                this.buttons.add(button);
                // @todo 1.14
//                actionHandler.put(id, () -> setProfile(profile));
                profileNames.put(id, profile.getName());
                id++;

                // @todo 1.14
//                GuiLabel label = new GuiLabel(Minecraft.getMinecraft().fontRenderer, id++, 110, y, 230, 20, 0xffffffff);
//                label.addLine(profile.getDescription());
//                this.labelList.add(label);
                y += 22;
            }
        }

        // @todo 1.14
//        y = 200;
//        GuiLabel label = new GuiLabel(Minecraft.getMinecraft().fontRenderer, id++, 20, y, 340, 20, 0xffffffff);
//        label.addLine("(note, you can create your own profiles and many more");
//        label.addLine("configuration options in 'lostcities.cfg')");
//        this.labelList.add(label);
//
//        if (numpages > 1) {
//            GuiButton prev = new GuiButton(id, 330, y, 20, 19, "<");
//            this.buttonList.add(prev);
//            actionHandler.put(id, () -> { page = page > 0 ? page - 1 : page; setupGui(profileName); });
//
//            id++;
//            pagelabel = new GuiMutableLabel(Minecraft.getMinecraft().fontRenderer, id++, 360, y, 30, 20, 0xffffffff);
//            pagelabel.addLine("" + (page+1) + "/" + numpages);
//
//            GuiButton next = new GuiButton(id, 390, y, 20, 19, ">");
//            this.buttonList.add(next);
//            actionHandler.put(id, () -> { page = page < numpages-1 ? page + 1 : page; setupGui(profileName); });
//            id++;
//        }
    }

    // @todo 1.14
//    private void setProfile(LostCityProfile profile) {
//        parent.chunkProviderSettingsJson = "{ \"profile\": \"" + profile.getName() + "\" }";
//        this.mc.displayGuiScreen(parent);
//    }
//
//
//    @Override
//    protected void actionPerformed(Button button) throws IOException {
//        super.actionPerformed(button);
//        if (actionHandler.containsKey(button.id)) {
//            actionHandler.get(button.id).run();
//        }
//    }
//
//    @Override
//    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        this.drawDefaultBackground();
//        super.drawScreen(mouseX, mouseY, partialTicks);
//        if (numpages > 1) {
//            pagelabel.clearLines();
//            pagelabel.addLine("" + (page+1) + "/" + numpages);
//            pagelabel.drawLabel(Minecraft.getMinecraft(), mouseX, mouseY);
//        }
//
//        for (GuiButton button : buttonList) {
//            if (button.isMouseOver()) {
//                String name = profileNames.get(button.id);
//                if (name != null) {
//                    LostCityProfile profile = LostCityConfiguration.profiles.get(name);
//                    if (profile != null && profile.getIcon() != null) {
//                        int bx = button.x + 95;
//                        int by = button.y + 6;
//                        drawGradientRect(bx-5, by-5, bx + 320, by + 85, 0xffffffff, 0xffffffff);
//                        mc.getTextureManager().bindTexture(profile.getIcon());
//                        drawScaledCustomSizeModalRect(bx, by, 0, 0, 128, 128, 80, 80, 128, 128);
//                        String output = profile.getDescription() + "\n" + profile.getExtraDescription();
//                        mc.fontRenderer.drawSplitString(output, bx + 90, by, 220, 0xff000000);
//                    }
//                }
//            }
//        }
//    }
}
