package com.bebo.seriespro.Common;

import com.bebo.seriespro.Module.Season;
import com.bebo.seriespro.Module.Show;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static List<Show> showList = new ArrayList<>();
    public static List<Show> downloadedShowList = new ArrayList<>();
    public static Show showSelected;
    public static String selectedEpisodeLink = "";
    public static int seasonIndex = 0;
    public static int episodeIndex = 0;
}
