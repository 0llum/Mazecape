package com.ollum.mazecape;

public class LevelArrays {
    public static final String[] MOVE_DOWN = {
            "ndn", "ndl", "ntu", "ndr", "nud", "ntr", "ntl", "ncr",
            "fdn", "fdl", "ftu", "fdr", "fud", "ftr", "ftl", "fcr",
            "sdn", "sdl", "stu", "sdr", "sud", "str", "stl", "scr",
            "pdn", "pdl", "ptu", "pdr", "pud", "ptr", "ptl", "pcr",
            "hdn", "hdl", "htu", "hdr", "hud", "htr", "htl", "hcr",
            "cdn", "cdl", "ctu", "cdr", "cud", "ctr", "ctl", "ccr",
            "bud"
    };
    public static final String[] MOVE_LEFT = {
            "ndl", "ntu", "nlt", "nlr", "ntr", "nul", "ntd", "ncr",
            "fdl", "ftu", "flt", "flr", "ftr", "ful", "ftd", "fcr",
            "sdl", "stu", "slt", "slr", "str", "sul", "std", "scr",
            "pdl", "ptu", "plt", "plr", "ptr", "pul", "ptd", "pcr",
            "hdl", "htu", "hlt", "hlr", "htr", "hul", "htd", "hcr",
            "cdl", "ctu", "clt", "clr", "ctr", "cul", "ctd", "ccr",
            "blr"
    };
    public static final String[] MOVE_RIGHT = {
            "ntu", "ndr", "nlr", "nrt", "ntl", "ntd", "nur", "ncr",
            "ftu", "fdr", "flr", "frt", "ftl", "ftd", "fur", "fcr",
            "stu", "sdr", "slr", "srt", "stl", "std", "sur", "scr",
            "ptu", "pdr", "plr", "prt", "ptl", "ptd", "pur", "pcr",
            "htu", "hdr", "hlr", "hrt", "htl", "htd", "hur", "hcr",
            "ctu", "cdr", "clr", "crt", "ctl", "ctd", "cur", "ccr",
            "blr"
    };
    public static final String[] MOVE_UP = {
            "nud", "ntr", "ntl", "nul", "ntd", "nup", "nur", "ncr",
            "fud", "ftr", "ftl", "ful", "ftd", "fup", "fur", "fcr",
            "sud", "str", "stl", "sul", "std", "sup", "sur", "scr",
            "pud", "ptr", "ptl", "pul", "ptd", "pup", "pur", "pcr",
            "hud", "htr", "htl", "hul", "htd", "huh", "hur", "hcr",
            "cud", "ctr", "ctl", "cul", "ctd", "cuc", "cur", "ccr",
            "bud"
    };
    public static final String[] GOAL = {
            "gdn", "gdl", "gtu", "gdr", "glt", "glr", "grt", "gud", "gtr", "gtl", "gup", "gul", "gtd", "gur", "gcr"
    };
    public static final String[] SWORD = {
            "sdn", "sdl", "stu", "sdr", "slt", "slr", "srt", "sud", "str", "stl", "sup", "sul", "std", "sur", "scr"
    };
    public static final String[] MONSTER = {
            "mdn", "mdl", "mtu", "mdr", "mlt", "mlr", "mrt", "mud", "mtr", "mtl", "mup", "mul", "mtd", "mur", "mcr"
    };
    public static final String[] FIRE = {
            "fdn", "fdl", "ftu", "fdr", "flt", "flr", "frt", "fud", "ftr", "ftl", "fup", "ful", "ftd", "fur", "fcr"
    };
    public static final String[] PORTAL = {
            "pdn", "pdl", "ptu", "pdr", "plt", "plr", "prt", "pud", "ptr", "ptl", "pup", "pul", "ptd", "pur", "pcr"
    };
    public static final String[] NORMAL = {
            "ndn", "ndl", "ntu", "ndr", "nlt", "nlr", "nrt", "nud", "ntr", "ntl", "nup", "nul", "ntd", "nur", "ncr"
    };
    public static final String[] HOLES = {
            "hdh", "hdl", "htu", "hdr", "hlt", "hlr", "hrt", "hud", "htr", "htl", "hup", "hul", "htd", "hur", "hcr"
    };
    public static final String[] TRAP = {
            "tdt", "tdl", "ttu", "tdr", "tlt", "tlr", "trt", "tud", "ttr", "ttl", "tup", "tul", "ttd", "tur", "tcr"
    };
    public static final String[] STAR = {
            "cdn", "cdl", "ctu", "cdr", "clt", "clr", "crt", "cud", "ctr", "ctl", "cup", "cul", "ctd", "cur", "ccr"
    };
        public static final String[][] FOREST_1 = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "nrt", "clr", "clr", "clr", "glt", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"2", "2", "f", "100", "4"},
                {}
    };
        public static final String[][] FOREST_2 = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "cdr", "nlr", "cdl", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "cdl", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nrt", "nul", "gup", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"2", "4", "f", "100", "8"},
                {}
    };
        public static final String[][] FOREST_3 = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "cdn", "ndr", "ntu", "nlt", "xxx", "xxx"},
                {"xxx", "xxx", "ntl", "nul", "nur", "ndl", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "ntu", "clt", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "crt", "nul", "grt", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"3", "2", "f", "100", "22"},
                {}
    };
        public static final String[][] FOREST_4 = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "cdr", "nlr", "cdl", "xxx", "xxx"},
                {"xxx", "xxx", "ntl", "mdl", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "cup", "gup", "sup", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"3", "2", "f", "100", "12"},
                {}
    };
        public static final String[][] FOREST_5 = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "gdn", "ndr", "slt", "wdn", "cdn", "xxx", "xxx"},
                {"xxx", "xxx", "mud", "nud", "wdr", "wul", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "ntl", "ntd", "blr", "nlr", "ntr", "xxx", "xxx"},
                {"xxx", "xxx", "cup", "wdr", "wul", "ndr", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "wrt", "wul", "crt", "ntd", "nlt", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"6", "6", "f", "100", "24"},
                {}
    };
        public static final String[][] CAVE_1 = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "nlr", "ndl", "ndr", "nlr", "ntu", "ntu", "nlr", "nlr", "ndl", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "crt", "nul", "nur", "ndl", "nur", "ntr", "cdn", "ndr", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "ndl", "ndr", "clt", "ntl", "ndl", "nur", "nul", "nud", "ndn", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "ntd", "nul", "ndr", "ntr", "nur", "flt", "ndr", "ntd", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "nlr", "nlr", "nul", "nur", "nlt", "nrt", "ntd", "nlr", "glt", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"2", "2", "c", "3", "57"},
                {}
    };
        public static final String[][] SNOW_1 = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "nrt", "clr", "clr", "clr", "glt", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"2", "2", "s", "100", "1"},
                {}
    };
        public static final String[][] SNOW_2 = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "ndn", "pdn", "xxx", "xxx"},
                {"xxx", "xxx", "cud", "cud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "cud", "xxx", "xxx"},
                {"xxx", "xxx", "pup", "gup", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"2", "2", "s", "100", "1"},
                {"2,5", "3,2"}
    };
        public static final String[][] LEVEL_B = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "wdr", "wlr", "wtu", "wlr", "wdl", "xxx"},
                {"xxx", "wud", "gdn", "wud", "cdn", "wud", "xxx"},
                {"xxx", "wud", "pup", "wud", "sud", "wud", "xxx"},
                {"xxx", "wtl", "wlr", "wtr", "tud", "wud", "xxx"},
                {"xxx", "wud", "pdn", "wud", "nud", "wud", "xxx"},
                {"xxx", "wud", "cud", "wup", "cud", "wud", "xxx"},
                {"xxx", "wud", "mur", "nlr", "nul", "wud", "xxx"},
                {"xxx", "wur", "wlr", "wlr", "wlr", "wul", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"4", "7", "d", "100", "15"},
                {"2,3", "2,5"}
    };
        public static final String[][] LEVEL_D = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "prt", "hlr", "nlr", "tlr", "plr", "glt", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"4", "2", "d", "100", "3"},
                {"2,2", "6,2"}
    };
        public static final String[][] LEVEL_J = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "nrt", "ndl", "ndr", "nlr", "ndl", "ndr", "ndl", "ndr", "nlr", "ndl", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "nul", "nud", "ndr", "nul", "nud", "nur", "nul", "nrt", "ntr", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "ndr", "nul", "nur", "ntu", "ntr", "fdn", "ndr", "ndl", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nur", "flt", "ndr", "nul", "nup", "nur", "nul", "nud", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "ndr", "ntu", "nul", "ndr", "nlr", "ndl", "ndn", "nud", "gup", "xxx", "xxx"},
                {"xxx", "xxx", "ntl", "nul", "nur", "ndl", "ntl", "ndl", "nur", "nul", "nur", "ndl", "xxx", "xxx"},
                {"xxx", "xxx", "ntl", "nlr", "nlt", "nud", "nud", "nur", "nlr", "nlr", "ndl", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "ndr", "ndl", "nur", "ncr", "nlr", "nlt", "ndr", "nul", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nud", "nur", "ndl", "nud", "ndr", "ndl", "nur", "nlr", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "nul", "frt", "nul", "nur", "nul", "nur", "nlr", "nlr", "nlt", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"11", "11", "d", "3", "30"},
                {}
    };
        public static final String[][] LEVEL_K = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "ndl", "ndr", "nlr", "ndl", "ndn", "ndr", "ntu", "ndl", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nup", "nud", "ndr", "nul", "nud", "ntl", "nul", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "ntl", "ndl", "nud", "nur", "nlr", "ntr", "nur", "ndl", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nud", "nud", "ndr", "ndl", "nud", "ndr", "nul", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nud", "nud", "ntl", "ncr", "nul", "nud", "ndr", "ntr", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nur", "nul", "nur", "ntr", "ndr", "nul", "nud", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "ndn", "ndr", "ndl", "nud", "nud", "ndr", "nul", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "ncr", "nul", "nud", "nup", "nud", "nup", "grt", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "nrt", "ntd", "nlr", "ntd", "nlr", "ftd", "nlr", "nlr", "nlt", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"6", "6", "d", "4", "52"},
                {}
    };
        public static final String[][] LEVEL_L = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "nlr", "ntu", "ndl", "ndr", "nlr", "ntu", "ntu", "nlr", "ndl", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "ndl", "nup", "nur", "nul", "nrt", "nul", "ntl", "nlt", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "ntd", "nlt", "ndn", "nrt", "ndl", "nrt", "nul", "ndr", "ntr", "xxx", "xxx"},
                {"xxx", "xxx", "ntl", "ntu", "nlt", "nur", "ndl", "nud", "ndr", "nlt", "nud", "nup", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nur", "nlr", "ndl", "nud", "nur", "ntd", "ndl", "nur", "ndl", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nrt", "nlr", "nul", "nud", "ndr", "nlr", "ntr", "ndn", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "nlr", "ndl", "ndr", "nul", "nud", "ndn", "nur", "ntr", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "ntu", "ntd", "nul", "ndr", "nul", "ntl", "ndl", "nud", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nup", "ntl", "ndl", "ndn", "nud", "nrt", "ntr", "ntl", "nul", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nrt", "nul", "nur", "nul", "nur", "glt", "nup", "nur", "nlr", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"6", "2", "c", "3", "32"},
                {}
    };
        public static final String[][] LEVEL_M = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "nlr", "ntu", "nlr", "nlr", "ndl", "ndr", "nlr", "nlr", "ntu", "nlr", "ntu", "nlt", "ndr", "ndl", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nrt", "ntd", "nlt", "ndn", "nur", "ncr", "nlr", "ntu", "ntd", "nlt", "nup", "ndr", "ntr", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "ntu", "nlr", "nlt", "nud", "nrt", "ntr", "nrt", "ntr", "ndr", "nlr", "ndl", "nud", "nup", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nrt", "ntd", "ntu", "nlr", "ncr", "nlt", "ntl", "nlt", "nup", "nud", "nrt", "ntd", "ntr", "ndr", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "nlr", "nul", "nrt", "nul", "ndr", "nul", "ndn", "ndr", "ntd", "nlt", "ndr", "ncr", "ntr", "ndn", "xxx", "xxx"},
                {"xxx", "xxx", "nup", "ndr", "nlr", "nlr", "ntu", "nul", "ndr", "nul", "nud", "ndr", "nlr", "ntr", "nud", "nur", "ntr", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "ntr", "nrt", "ndl", "nur", "ntu", "nul", "ndr", "ntr", "nup", "ndr", "nul", "nud", "ndr", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "ntl", "nlr", "nul", "nrt", "nul", "ndr", "nul", "nup", "ndr", "ntr", "ndr", "nul", "ntl", "ndl", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "ntl", "nlr", "ntu", "nlr", "ntu", "nul", "ndr", "ndl", "nup", "ntl", "nul", "ndr", "ntr", "nup", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "ntd", "ndl", "nud", "ndn", "nud", "nrt", "ntr", "nur", "nlt", "ntl", "ndl", "nud", "nud", "ndn", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "nlr", "ntr", "ntl", "ntr", "ntl", "nlt", "ntl", "nlt", "ndr", "nul", "nud", "nud", "nur", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "ndr", "ntd", "nul", "nur", "ntr", "ndr", "nul", "ndn", "nud", "ndn", "nud", "nur", "nlr", "glt", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nud", "ndr", "ntu", "ndl", "nur", "ntr", "ndr", "ntr", "nur", "ntd", "ncr", "nlr", "ndl", "ndn", "xxx", "xxx"},
                {"xxx", "xxx", "ntl", "ntd", "nul", "nud", "nur", "ndl", "nur", "ntr", "nur", "ndl", "ndn", "nud", "ndr", "nul", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "nlr", "nlr", "nul", "nrt", "ntd", "nlt", "nur", "nlr", "nul", "nur", "ntd", "ntd", "nlr", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"2", "4", "s", "8", "53"},
                {}
        };
        public static final String[][] LEVEL_N = {
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "ntu", "nlt", "ndr", "nlr", "nlr", "nlr", "nlr", "ndl", "nrt", "nlr", "ndl", "ndr", "nlr", "nlr", "nlr", "ntu", "nlr", "nlr", "nlt", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nup", "ndr", "nul", "ndr", "ntu", "nlr", "ndl", "ntl", "nlr", "ndl", "nud", "nur", "nlr", "ndl", "ndn", "nur", "ndl", "ndr", "nur", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "ndr", "nul", "nrt", "nul", "nur", "ndl", "nud", "nur", "ndl", "nup", "nur", "ndl", "ndn", "nur", "ntr", "ndn", "nur", "nul", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nur", "nlr", "nlr", "nlr", "nlr", "nul", "nud", "ndr", "ntr", "ndr", "nlr", "nul", "ntl", "nlt", "nur", "ntd", "ndl", "ndn", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "ndr", "nlr", "nlr", "ntu", "nlr", "nlt", "nud", "nud", "nup", "nur", "nlr", "ndl", "nur", "nlr", "ntu", "nlr", "ntd", "nul", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nur", "nlr", "ndl", "nur", "nlr", "nlr", "nul", "nud", "ndr", "nlr", "ndl", "nur", "nlr", "ndl", "nud", "ndn", "ndr", "nlr", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "ntl", "ntu", "ndl", "nur", "nlr", "nlr", "ndl", "ndn", "nud", "ntl", "nlt", "nur", "nlr", "ndl", "nud", "nur", "ntr", "nur", "nlr", "ndl", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nud", "nup", "ndr", "nlr", "nlr", "nul", "nud", "nur", "nul", "ndr", "nlr", "ndl", "nud", "nud", "nrt", "ntd", "nlr", "nlt", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nur", "nlr", "nul", "ndr", "nlr", "nlr", "ntr", "ndr", "nlr", "nul", "ndn", "nur", "ntr", "nur", "nlr", "nlr", "nlr", "ndl", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "ndr", "ndl", "ndr", "nul", "nrt", "ndl", "nud", "nur", "nlr", "ndl", "nur", "ndl", "nur", "nlr", "nlr", "nlr", "ndl", "nur", "ntr", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nup", "ntl", "nul", "nrt", "ntu", "ntr", "nur", "ntu", "ndl", "nur", "ndl", "ntl", "ndl", "ndr", "nlr", "ndr", "nud", "ndr", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "ndl", "nur", "nlr", "nlr", "nul", "nur", "ndl", "nud", "nur", "ndl", "nud", "nup", "nur", "ntd", "ndl", "nur", "nul", "nud", "ndn", "xxx", "xxx"},
                {"xxx", "xxx", "ndn", "nud", "ndr", "ndl", "ndr", "ndl", "nrt", "nul", "nud", "ndr", "nul", "nur", "nlr", "nlr", "ndl", "nur", "nlr", "ndl", "nud", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nur", "nul", "nur", "nul", "nud", "ndr", "nlr", "nul", "nud", "ndr", "nlt", "ndr", "nlr", "nul", "nrt", "ndl", "nud", "nur", "ntr", "xxx", "xxx"},
                {"xxx", "xxx", "ntl", "ntu", "nlr", "nlt", "ndr", "nul", "nud", "ndr", "ndl", "ntl", "nul", "ndr", "nul", "nrt", "ntu", "ndl", "nud", "nud", "ndr", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "nur", "ndl", "ndr", "nul", "ndr", "nul", "nud", "nur", "nul", "ndr", "nul", "nrt", "ntu", "nul", "nur", "nul", "nud", "nud", "ndn", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "ndl", "nud", "nud", "ndr", "ntd", "ndl", "nud", "ndn", "ndr", "nul", "ndr", "ndl", "nur", "ndl", "ndr", "ndl", "nud", "nud", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "ndr", "nul", "nur", "nul", "nud", "ndr", "nul", "nur", "ntr", "nur", "nlr", "nul", "nur", "ndl", "nur", "nul", "nur", "nul", "nud", "nud", "xxx", "xxx"},
                {"xxx", "xxx", "nud", "ndr", "nlr", "ndl", "nup", "nud", "ndr", "nlt", "nud", "nrt", "ntu", "ndl", "ndr", "nul", "ndr", "nlr", "nlr", "ndl", "nur", "ntr", "xxx", "xxx"},
                {"xxx", "xxx", "nur", "nul", "nrt", "ntd", "nlr", "nul", "nur", "nlr", "ntd", "nlr", "gul", "nup", "nur", "nlr", "ntd", "nlr", "nlt", "nur", "nlr", "nul", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
                {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"11", "2", "c", "20", "224"},
                {}
    };
    public static final String[][][] LEVEL = {
            FOREST_1, FOREST_2, FOREST_3, FOREST_4, FOREST_5, CAVE_1
    };
    public static final String[][] TEMPLATE_3x3 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"0", "0", "f", "100", "100"},
            {}
    };
    public static final String[][] TEMPLATE_5x5 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"0", "0", "f", "100", "100"},
            {}
    };
    public static final String[][] TEMPLATE_10x10 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"0", "0", "f", "100", "100"},
            {}
    };
    public static final String[][] TEMPLATE_20x20 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"0", "0", "f", "100", "100"},
            {}
    };
}
