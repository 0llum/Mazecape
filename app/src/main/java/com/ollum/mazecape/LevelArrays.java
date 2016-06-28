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
    public static final String[] EDGE = {
            "etl", "etp", "etr", "elt", "ect", "ert", "ebl", "ebt", "ebr"
    };
    public static final String[] STAR = {
            "cdn", "cdl", "ctu", "cdr", "clt", "clr", "crt", "cud", "ctr", "ctl", "cup", "cul", "ctd", "cur", "ccr"
    };
    public static final String[][] LEVEL_A = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "gdn", "ndr", "clt", "wdn", "cdn", "ect", "ert"},
            {"elt", "ect", "nud", "nud", "wdr", "wul", "nud", "ect", "ert"},
            {"elt", "ect", "ntl", "ntd", "blr", "nlr", "ntr", "ect", "ert"},
            {"elt", "ect", "nup", "wdr", "wul", "ndr", "nul", "ect", "ert"},
            {"elt", "ect", "wrt", "wul", "crt", "ntd", "nlt", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"6", "6", "f", "100", "22"}
    };
    public static final String[][] LEVEL_B = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "wdr", "wlr", "wtu", "wlr", "wdl", "ert"},
            {"elt", "wud", "gdn", "wud", "cdn", "wud", "ert"},
            {"elt", "wud", "pup", "wud", "sud", "wud", "ert"},
            {"elt", "wtl", "wlr", "wtr", "tud", "wud", "ert"},
            {"elt", "wud", "pdn", "wud", "nud", "wud", "ert"},
            {"elt", "wud", "cud", "wup", "cud", "wud", "ert"},
            {"elt", "wud", "mur", "nlr", "nul", "wud", "ert"},
            {"elt", "wur", "wlr", "wlr", "wlr", "wul", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"4", "7", "f", "100", "15"}
    };
    public static final String[][] LEVEL_0 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "nrt", "tlr", "glt", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"2", "2", "s", "100", "2"}
    };
    public static final String[][] LEVEL_1 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "prt", "hlr", "nlr", "tlr", "plr", "glt", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"4", "2", "f", "100", "3"}
    };
    public static final String[][] LEVEL_2 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ndr", "nlr", "pdl", "ect", "ert"},
            {"elt", "ect", "nur", "pdl", "nud", "ect", "ert"},
            {"elt", "ect", "nrt", "nul", "gup", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"2", "4", "s", "2", "8"}
    };
    public static final String[][] LEVEL_3 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ndr", "nlr", "ndl", "ect", "ert"},
            {"elt", "ect", "ntl", "mdl", "nud", "ect", "ert"},
            {"elt", "ect", "nup", "gup", "sup", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"3", "2", "s", "2", "4"}
    };
    public static final String[][] LEVEL_4 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "cdn", "ndr", "ntu", "nlt", "ect", "ert"},
            {"elt", "ect", "ntl", "nul", "nur", "ndl", "ect", "ert"},
            {"elt", "ect", "nur", "ntu", "clt", "nud", "ect", "ert"},
            {"elt", "ect", "crt", "nul", "grt", "nul", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"3", "2", "f", "2", "22"}
    };
    public static final String[][] LEVEL_5 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ndr", "nlr", "nlr", "ndl", "ect", "ert"},
            {"elt", "ect", "nud", "ndr", "nlr", "ntr", "ect", "ert"},
            {"elt", "ect", "nud", "nur", "ndl", "nud", "ect", "ert"},
            {"elt", "ect", "nur", "nlt", "gup", "nup", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"3", "2", "f", "2", "8"}
    };
    public static final String[][] LEVEL_6 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ndr", "nlr", "ndl", "ndr", "nlr", "ntu", "ntu", "nlr", "nlr", "ndl", "ect", "ert"},
            {"elt", "ect", "nud", "nrt", "nul", "nur", "ndl", "nur", "ntr", "ndn", "ndr", "nul", "ect", "ert"},
            {"elt", "ect", "nur", "ndl", "ndr", "nlt", "ntl", "ndl", "nur", "nul", "nud", "ndn", "ect", "ert"},
            {"elt", "ect", "ndr", "ntd", "nul", "ndr", "ntr", "nur", "flt", "ndr", "ntd", "nul", "ect", "ert"},
            {"elt", "ect", "nur", "nlr", "nlr", "nul", "nur", "nlt", "nrt", "ntd", "nlr", "glt", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"2", "2", "f", "3", "29"}
    };
    public static final String[][] LEVEL_7 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "nrt", "ndl", "ndr", "nlr", "ndl", "ndr", "ndl", "ndr", "nlr", "ndl", "ect", "ert"},
            {"elt", "ect", "ndr", "nul", "nud", "ndr", "nul", "nud", "nur", "nul", "nrt", "ntr", "ect", "ert"},
            {"elt", "ect", "nud", "ndr", "nul", "nur", "ntu", "ntr", "fdn", "ndr", "ndl", "nud", "ect", "ert"},
            {"elt", "ect", "nud", "nur", "flt", "ndr", "nul", "nup", "nur", "nul", "nud", "nud", "ect", "ert"},
            {"elt", "ect", "nud", "ndr", "ntu", "nul", "ndr", "nlr", "ndl", "ndn", "nud", "gup", "ect", "ert"},
            {"elt", "ect", "ntl", "nul", "nur", "ndl", "ntl", "ndl", "nur", "nul", "nur", "ndl", "ect", "ert"},
            {"elt", "ect", "ntl", "nlr", "nlt", "nud", "nud", "nur", "nlr", "nlr", "ndl", "nud", "ect", "ert"},
            {"elt", "ect", "nud", "ndr", "ndl", "nur", "ncr", "nlr", "nlt", "ndr", "nul", "nud", "ect", "ert"},
            {"elt", "ect", "nud", "nud", "nur", "ndl", "nud", "ndr", "ndl", "nur", "nlr", "nul", "ect", "ert"},
            {"elt", "ect", "nur", "nul", "frt", "nul", "nur", "nul", "nur", "nlr", "nlr", "nlt", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"11", "11", "f", "3", "30"}
    };
    public static final String[][] LEVEL_8 = {
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ndr", "ndl", "ndr", "nlr", "ndl", "ndn", "ndr", "ntu", "ndl", "ect", "ect"},
            {"ect", "ect", "nud", "nup", "nud", "ndr", "nul", "nud", "ntl", "nul", "nud", "ect", "ect"},
            {"ect", "ect", "ntl", "ndl", "nud", "nur", "nlr", "ntr", "nur", "ndl", "nud", "ect", "ect"},
            {"ect", "ect", "nud", "nud", "nud", "ndr", "ndl", "nud", "ndr", "nul", "nud", "ect", "ect"},
            {"ect", "ect", "nud", "nud", "nud", "ntl", "ncr", "nul", "nud", "ndr", "ntr", "ect", "ect"},
            {"ect", "ect", "nud", "nur", "nul", "nur", "ntr", "ndr", "nul", "nud", "nud", "ect", "ect"},
            {"ect", "ect", "nud", "ndn", "ndr", "ndl", "nud", "nud", "ndr", "nul", "nud", "ect", "ect"},
            {"ect", "ect", "nur", "ncr", "nul", "nud", "nup", "nud", "nup", "grt", "nul", "ect", "ect"},
            {"ect", "ect", "nrt", "ntd", "nlr", "ntd", "nlr", "ftd", "nlr", "nlr", "nlt", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"6", "6", "s", "4", "52"}
    };
    public static final String[][] LEVEL_9 = {
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ndr", "nlr", "ntu", "ndl", "ndr", "nlr", "ntu", "ntu", "nlr", "ndl", "ect", "ect"},
            {"ect", "ect", "nur", "ndl", "nup", "nur", "nul", "nrt", "nul", "ntl", "nlt", "nud", "ect", "ect"},
            {"ect", "ect", "ndr", "ntd", "nlt", "ndn", "nrt", "ndl", "nrt", "nul", "ndr", "ntr", "ect", "ect"},
            {"ect", "ect", "ntl", "ntu", "nlt", "nur", "ndl", "nud", "ndr", "nlt", "nud", "nup", "ect", "ect"},
            {"ect", "ect", "nud", "nur", "nlr", "ndl", "nud", "nur", "ntd", "ndl", "nur", "ndl", "ect", "ect"},
            {"ect", "ect", "nud", "nrt", "nlr", "nul", "nud", "ndr", "nlr", "ntr", "ndn", "nud", "ect", "ect"},
            {"ect", "ect", "nur", "nlr", "ndl", "ndr", "nul", "nud", "ndn", "nur", "ntr", "nud", "ect", "ect"},
            {"ect", "ect", "ndr", "ntu", "ntd", "nul", "ndr", "nul", "ntl", "ndl", "nud", "nud", "ect", "ect"},
            {"ect", "ect", "nup", "ntl", "ndl", "ndn", "nud", "nrt", "ntr", "ntl", "nul", "nud", "ect", "ect"},
            {"ect", "ect", "nrt", "nul", "nur", "nul", "nur", "glt", "nup", "nur", "nlr", "nul", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"6", "2", "c", "3", "32"}
    };
    public static final String[][] LEVEL_10 = {
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ndr", "nlr", "ntu", "nlr", "nlr", "ndl", "ndr", "nlr", "nlr", "ntu", "nlr", "ntu", "nlt", "ndr", "ndl", "ect", "ect"},
            {"ect", "ect", "nud", "nrt", "ntd", "nlt", "ndn", "nur", "ncr", "nlr", "ntu", "ntd", "nlt", "nup", "ndr", "ntr", "nud", "ect", "ect"},
            {"ect", "ect", "nur", "ntu", "nlr", "nlt", "nud", "nrt", "ntr", "nrt", "ntr", "ndr", "nlr", "ndl", "nud", "nup", "nud", "ect", "ect"},
            {"ect", "ect", "nrt", "ntd", "ntu", "nlr", "ncr", "nlt", "ntl", "nlt", "nup", "nud", "nrt", "ntd", "ntr", "ndr", "nul", "ect", "ect"},
            {"ect", "ect", "ndr", "nlr", "nul", "nrt", "nul", "ndr", "nul", "ndn", "ndr", "ntd", "nlt", "ndr", "ncr", "ntr", "ndn", "ect", "ect"},
            {"ect", "ect", "nup", "ndr", "nlr", "nlr", "ntu", "nul", "ndr", "nul", "nud", "ndr", "nlr", "ntr", "nud", "nur", "ntr", "ect", "ect"},
            {"ect", "ect", "ndr", "ntr", "nrt", "ndl", "nur", "ntu", "nul", "ndr", "ntr", "nup", "ndr", "nul", "nud", "ndr", "nul", "ect", "ect"},
            {"ect", "ect", "nud", "ntl", "nlr", "nul", "nrt", "nul", "ndr", "nul", "nup", "ndr", "ntr", "ndr", "nul", "ntl", "ndl", "ect", "ect"},
            {"ect", "ect", "nud", "ntl", "nlr", "ntu", "nlr", "ntu", "nul", "ndr", "ndl", "nup", "ntl", "nul", "ndr", "ntr", "nup", "ect", "ect"},
            {"ect", "ect", "nur", "ntd", "ndl", "nud", "ndn", "nud", "nrt", "ntr", "nur", "nlt", "ntl", "ndl", "nud", "nud", "ndn", "ect", "ect"},
            {"ect", "ect", "ndr", "nlr", "ntr", "ntl", "ntr", "ntl", "nlt", "ntl", "nlt", "ndr", "nul", "nud", "nud", "nur", "nul", "ect", "ect"},
            {"ect", "ect", "nud", "ndr", "ntd", "nul", "nur", "ntr", "ndr", "nul", "ndn", "nud", "ndn", "nud", "nur", "nlr", "glt", "ect", "ect"},
            {"ect", "ect", "nud", "nud", "ndr", "ntu", "ndl", "nur", "ntr", "ndr", "ntr", "nur", "ntd", "ncr", "nlr", "ndl", "ndn", "ect", "ect"},
            {"ect", "ect", "ntl", "ntd", "nul", "nud", "nur", "ndl", "nur", "ntr", "nur", "ndl", "ndn", "nud", "ndr", "nul", "nud", "ect", "ect"},
            {"ect", "ect", "nur", "nlr", "nlr", "nul", "nrt", "ntd", "nlt", "nur", "nlr", "nul", "nur", "ntd", "ntd", "nlr", "nul", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"2", "4", "s", "8", "53"}
    };
    public static final String[][] LEVEL_100 = {
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ndr", "ntu", "nlt", "ndr", "nlr", "nlr", "nlr", "nlr", "ndl", "nrt", "nlr", "ndl", "ndr", "nlr", "nlr", "nlr", "ntu", "nlr", "nlr", "nlt", "ect", "ect"},
            {"ect", "ect", "nud", "nup", "ndr", "nul", "ndr", "ntu", "nlr", "ndl", "ntl", "nlr", "ndl", "nud", "nur", "nlr", "ndl", "ndn", "nur", "ndl", "ndr", "nur", "ect", "ect"},
            {"ect", "ect", "nud", "ndr", "nul", "nrt", "nul", "nur", "ndl", "nud", "nur", "ndl", "nup", "nur", "ndl", "ndn", "nur", "ntr", "ndn", "nur", "nul", "nud", "ect", "ect"},
            {"ect", "ect", "nud", "nur", "nlr", "nlr", "nlr", "nlr", "nul", "nud", "ndr", "ntr", "ndr", "nlr", "nul", "ntl", "nlt", "nur", "ntd", "ndl", "ndn", "nud", "ect", "ect"},
            {"ect", "ect", "nud", "ndr", "nlr", "nlr", "ntu", "nlr", "nlt", "nud", "nud", "nup", "nur", "nlr", "ndl", "nur", "nlr", "ntu", "nlr", "ntd", "nul", "nud", "ect", "ect"},
            {"ect", "ect", "nud", "nur", "nlr", "ndl", "nur", "nlr", "nlr", "nul", "nud", "ndr", "nlr", "ndl", "nur", "nlr", "ndl", "nud", "ndn", "ndr", "nlr", "nul", "ect", "ect"},
            {"ect", "ect", "ntl", "ntu", "ndl", "nur", "nlr", "nlr", "ndl", "ndn", "nud", "ntl", "nlt", "nur", "nlr", "ndl", "nud", "nur", "ntr", "nur", "nlr", "ndl", "ect", "ect"},
            {"ect", "ect", "nud", "nud", "nup", "ndr", "nlr", "nlr", "nul", "nud", "nur", "nul", "ndr", "nlr", "ndl", "nud", "nud", "nrt", "ntd", "nlr", "nlt", "nud", "ect", "ect"},
            {"ect", "ect", "nud", "nur", "nlr", "nul", "ndr", "nlr", "nlr", "ntr", "ndr", "nlr", "nul", "ndn", "nur", "ntr", "nur", "nlr", "nlr", "nlr", "ndl", "nud", "ect", "ect"},
            {"ect", "ect", "nud", "ndr", "ndl", "ndr", "nul", "nrt", "ndl", "nud", "nur", "nlr", "ndl", "nur", "ndl", "nur", "nlr", "nlr", "nlr", "ndl", "nur", "ntr", "ect", "ect"},
            {"ect", "ect", "nud", "nup", "ntl", "nul", "nrt", "ntu", "ntr", "nur", "ntu", "ndl", "nur", "ndl", "ntl", "ndl", "ndr", "nlr", "ndr", "nud", "ndr", "nul", "ect", "ect"},
            {"ect", "ect", "nur", "ndl", "nur", "nlr", "nlr", "nul", "nur", "ndl", "nud", "nur", "ndl", "nud", "nup", "nur", "ntd", "ndl", "nur", "nul", "nud", "ndn", "ect", "ect"},
            {"ect", "ect", "ndn", "nud", "ndr", "ndl", "ndr", "ndl", "nrt", "nul", "nud", "ndr", "nul", "nur", "nlr", "nlr", "ndl", "nur", "nlr", "ndl", "nud", "nud", "ect", "ect"},
            {"ect", "ect", "nud", "nur", "nul", "nur", "nul", "nud", "ndr", "nlr", "nul", "nud", "ndr", "nlt", "ndr", "nlr", "nul", "nrt", "ndl", "nud", "nur", "ntr", "ect", "ect"},
            {"ect", "ect", "ntl", "ntu", "nlr", "nlt", "ndr", "nul", "nud", "ndr", "ndl", "ntl", "nul", "ndr", "nul", "nrt", "ntu", "ndl", "nud", "nud", "ndr", "nul", "ect", "ect"},
            {"ect", "ect", "nud", "nur", "ndl", "ndr", "nul", "ndr", "nul", "nud", "nur", "nul", "ndr", "nul", "nrt", "ntu", "nul", "nur", "nul", "nud", "nud", "ndn", "ect", "ect"},
            {"ect", "ect", "nur", "ndl", "nud", "nud", "ndr", "ntd", "ndl", "nud", "ndn", "ndr", "nul", "ndr", "ndl", "nur", "ndl", "ndr", "ndl", "nud", "nud", "nud", "ect", "ect"},
            {"ect", "ect", "ndr", "nul", "nur", "nul", "nud", "ndr", "nul", "nur", "ntr", "nur", "nlr", "nul", "nur", "ndl", "nur", "nul", "nur", "nul", "nud", "nud", "ect", "ect"},
            {"ect", "ect", "nud", "ndr", "nlr", "ndl", "nup", "nud", "ndr", "nlt", "nud", "nrt", "ntu", "ndl", "ndr", "nul", "ndr", "nlr", "nlr", "ndl", "nur", "ntr", "ect", "ect"},
            {"ect", "ect", "nur", "nul", "nrt", "ntd", "nlr", "nul", "nur", "nlr", "ntd", "nlr", "gul", "nup", "nur", "nlr", "ntd", "nlr", "nlt", "nur", "nlr", "nul", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect"},
            {"11", "2", "c", "20", "224"},
    };
    public static final String[][][] LEVEL = {
            LEVEL_B, LEVEL_A, LEVEL_0, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_6, LEVEL_7, LEVEL_8, LEVEL_9, LEVEL_10, LEVEL_100
    };
    public static final String[][] TEMPLATE_3x3 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"0", "0", "f", "100", "100"}
    };
    public static final String[][] TEMPLATE_5x5 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"0", "0", "f", "100", "100"}
    };
    public static final String[][] TEMPLATE_10x10 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"0", "0", "f", "100", "100"}
    };
    public static final String[][] TEMPLATE_20x20 = {
            {"etl", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etp", "etr"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"elt", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ect", "ert"},
            {"ebl", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebt", "ebr"},
            {"0", "0", "f", "100", "100"},
    };
}
