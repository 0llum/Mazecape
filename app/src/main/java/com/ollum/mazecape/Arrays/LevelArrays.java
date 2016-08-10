package com.ollum.mazecape.Arrays;

public class LevelArrays {
    public static final String[] MOVE_DOWN = {
            "ndn", "ndl", "ntu", "ndr", "nud", "ntr", "ntl", "ncs",
            "fdn", "fdl", "ftu", "fdr", "fud", "ftr", "ftl", "fcs",
            "wdn", "wdl", "wtu", "wdr", "wud", "wtr", "wtl", "wcs",
            "pdn", "pdl", "ptu", "pdr", "pud", "ptr", "ptl", "pcs",
            "idn", "idl", "itu", "idr", "iud", "itr", "itl", "ics",
            "sdn", "sdl", "stu", "sdr", "sud", "str", "stl", "scs",
            "bud",
            "cdn", "cdl", "ctu", "cdr", "cud", "ctr", "ctl", "ccs",
            "jdn", "jtl", "jtr", "jrd", "jld", "jcd", "jcl", "jcr"
    };
    public static final String[] MOVE_LEFT = {
            "ndl", "ntu", "nlt", "nlr", "ntr", "nul", "ntd", "ncs",
            "fdl", "ftu", "flt", "flr", "ftr", "ful", "ftd", "fcs",
            "wdl", "wtu", "wlt", "wlr", "wtr", "wul", "wtd", "wcs",
            "pdl", "ptu", "plt", "plr", "ptr", "pul", "ptd", "pcs",
            "idl", "itu", "ilt", "ilr", "itr", "iul", "itd", "ics",
            "sdl", "stu", "slt", "slr", "str", "sul", "std", "scs",
            "blr",
            "cdl", "ctu", "clt", "clr", "ctr", "cul", "ctd", "ccs",
            "jlt", "jtu", "jtd", "jdl", "jul", "jcd", "jcl", "jcu"
    };
    public static final String[] MOVE_RIGHT = {
            "ntu", "ndr", "nlr", "nrt", "ntl", "ntd", "nur", "ncs",
            "ftu", "fdr", "flr", "frt", "ftl", "ftd", "fur", "fcs",
            "wtu", "wdr", "wlr", "wrt", "wtl", "wtd", "wur", "wcs",
            "ptu", "pdr", "plr", "prt", "ptl", "ptd", "pur", "pcs",
            "itu", "idr", "ilr", "irt", "itl", "itd", "iur", "ics",
            "stu", "sdr", "slr", "srt", "stl", "std", "sur", "scs",
            "blr",
            "ctu", "cdr", "clr", "crt", "ctl", "ctd", "cur", "ccs",
            "jrt", "jtu", "jtd", "jdr", "jur", "jcd", "jcu", "jcr"
    };
    public static final String[] MOVE_UP = {
            "nud", "ntr", "ntl", "nul", "ntd", "nup", "nur", "ncs",
            "fud", "ftr", "ftl", "ful", "ftd", "fup", "fur", "fcs",
            "wud", "wtr", "wtl", "wul", "wtd", "wup", "wur", "wcs",
            "pud", "ptr", "ptl", "pul", "ptd", "pup", "pur", "pcs",
            "iud", "itr", "itl", "iul", "itd", "hup", "iur", "ics",
            "sud", "str", "stl", "sul", "std", "sup", "sur", "scs",
            "bud",
            "cud", "ctr", "ctl", "cul", "ctd", "cup", "cur", "ccs",
            "jup", "jtl", "jtr", "jru", "jlu", "jcl", "jcu", "jcr"
    };
    public static final String[] GOAL = {
            "gup", "grt", "gdn", "glt", "gud", "glr", "gur", "gdr", "gdl", "gul", "gtu", "gtr", "gtd", "gtl", "gcs"
    };
    public static final String[] WEAPON = {
            "wup", "wrt", "wdn", "wlt", "wud", "wlr", "wur", "wdr", "wdl", "wul", "wtu", "wtr", "wtd", "wtl", "wcs"
    };
    public static final String[] MONSTER = {
            "mup", "mrt", "mdn", "mlt", "mud", "mlr", "mur", "mdr", "mdl", "mul", "mtu", "mtr", "mtd", "mtl", "mcs"
    };
    public static final String[] FIRE = {
            "fup", "frt", "fdn", "flt", "fud", "flr", "fur", "fdr", "fdl", "ful", "ftu", "ftr", "ftd", "ftl", "fcs"
    };
    public static final String[] PORTAL = {
            "pup", "prt", "pdn", "plt", "pud", "plr", "pur", "pdr", "pdl", "pul", "ptu", "ptr", "ptd", "ptl", "pcs"
    };
    public static final String[] NORMAL = {
            "nup", "nrt", "ndn", "nlt", "nud", "nlr", "nur", "ndr", "ndl", "nul", "ntu", "ntr", "ntd", "ntl", "ncs"
    };
    public static final String[] TRAP_INACTIVE = {
            "iup", "irt", "idn", "ilt", "iud", "ilr", "iur", "idr", "idl", "iul", "itu", "itr", "itd", "itl", "ics"
    };
    public static final String[] TRAP_ACTIVE = {
            "tup", "trt", "tdn", "tlt", "tud", "tlr", "tur", "tdr", "tdl", "tul", "ttu", "ttr", "ttd", "ttl", "tcs"
    };
    public static final String[] STAR = {
            "sup", "srt", "sdn", "slt", "sud", "slr", "sur", "sdr", "sdl", "sul", "stu", "str", "std", "stl", "scs"
    };
    public static final String[] CRACK = {
            "cup", "crt", "cdn", "clt", "cud", "clr", "cur", "cdr", "cdl", "cul", "ctu", "ctr", "ctd", "ctl", "ccs"
    };
    public static final String[] HOLE = {
            "hup", "hrt", "hdn", "hlt", "hud", "hlr", "hur", "hdr", "hdl", "hul", "htu", "htr", "htd", "htl", "hcs"
    };
    public static final String[] JUMP = {
            "jup", "jrt", "jdn", "jlt", "jtl", "jtr", "jtu", "jtd", "jur", "jru", "jrd", "jdr", "jld", "jdl", "jlu", "jul", "jcd", "jcl", "jcu", "jcr"
    };
    public static final String[][] TILES = {
            NORMAL, GOAL, STAR, FIRE, PORTAL, MONSTER, WEAPON, CRACK, TRAP_ACTIVE
    };
    public static final String[][] FOREST_1 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "slr", "slr", "slr", "glt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"3", "3", "f", "2", "4"},
            {},
            {}
    };
    public static final String[][] FOREST_2 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sdr", "clr", "sdl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "sdl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "nul", "gup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"3", "5", "f", "2", "8"},
            {},
            {}
    };
    public static final String[][] FOREST_3 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sdn", "ndr", "ntu", "nlt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "nul", "nur", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ntu", "slt", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "srt", "nul", "grt", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"4", "3", "f", "5", "22"},
            {},
            {}
    };
    public static final String[][] FOREST_4 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sdr", "nlr", "sdl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "mdl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sup", "gup", "wup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"4", "3", "f", "3", "12"},
            {},
            {}
    };
    public static final String[][] FOREST_5 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "gdn", "ndr", "wlt", "rdn", "sdn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "mud", "nud", "rdr", "rul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "ntd", "blr", "nlr", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sup", "rdr", "rul", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "rrt", "rul", "srt", "ntd", "nlt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"7", "7", "f", "5", "24"},
            {},
            {}
    };
    public static final String[][] FOREST_6 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "rdr", "rlr", "rtu", "rlr", "rdl", "xxx", "xxx"},
            {"xxx", "xxx", "rud", "gdn", "rud", "sdn", "rud", "xxx", "xxx"},
            {"xxx", "xxx", "rud", "pup", "rud", "wud", "rud", "xxx", "xxx"},
            {"xxx", "xxx", "rtl", "rlr", "rtr", "nud", "rud", "xxx", "xxx"},
            {"xxx", "xxx", "rud", "pdn", "rud", "nud", "rud", "xxx", "xxx"},
            {"xxx", "xxx", "rud", "sud", "rup", "sud", "rud", "xxx", "xxx"},
            {"xxx", "xxx", "rud", "mur", "nlr", "nul", "rud", "xxx", "xxx"},
            {"xxx", "xxx", "rur", "rlr", "rlr", "rlr", "rul", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"5", "8", "f", "3", "15"},
            {"3,4", "3,6"},
            {}
    };
    public static final String[][] FOREST_7 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "prt", "nlr", "wlt", "ndn", "nrt", "ntu", "plt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "srt", "ndl", "prt", "ntd", "plt", "ntl", "nlt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "prt", "ntd", "ntu", "mtu", "ntu", "ntd", "plt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "ntu", "nul", "nud", "nur", "ntu", "nlt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "prt", "ntd", "slt", "gup", "srt", "ntd", "plt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"6", "3", "f", "7", "33"},
            {"5,4", "3,7", "7,4", "9,7", "3,3", "9,5", "3,5", "9,3"},
            {}
    };
    public static final String[][] CAVE_1 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "ndl", "ndr", "nlr", "ntu", "ntu", "nlr", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "srt", "nul", "nur", "ndl", "nur", "ntr", "sdn", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ndl", "ndr", "slt", "ntl", "ndl", "nur", "nul", "nud", "ndn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ntd", "nul", "ndr", "ntr", "nur", "flt", "ndr", "ntd", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nlr", "nlr", "nul", "nur", "nlt", "nrt", "ntd", "nlr", "glt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"3", "3", "c", "3", "57"},
            {},
            {}
    };
    public static final String[][] CAVE_2 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ndl", "ndr", "nlr", "ndl", "sdn", "ndr", "ntu", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "sup", "nud", "ndr", "nul", "nud", "ntl", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "ndl", "nud", "nur", "nlr", "ntr", "nur", "ndl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nud", "nud", "ndr", "ndl", "nud", "ndr", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nud", "nud", "ntl", "ncs", "nul", "nud", "ndr", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nur", "nul", "nur", "ntr", "ndr", "nul", "nud", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndn", "ndr", "ndl", "nud", "nud", "ndr", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ncs", "nul", "nud", "nup", "nud", "nup", "grt", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "ntd", "nlr", "ntd", "nlr", "ftd", "nlr", "nlr", "slt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"7", "7", "c", "4", "70"},
            {},
            {}
    };
    public static final String[][] CAVE_3 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "ntu", "nlr", "nlr", "ndl", "ndr", "nlr", "nlr", "ntu", "nlr", "ntu", "nlt", "ndr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nrt", "ntd", "nlt", "ndn", "nur", "ncs", "nlr", "ntu", "ntd", "nlt", "nup", "ndr", "ntr", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ntu", "nlr", "nlt", "nud", "nrt", "ntr", "nrt", "ntr", "ndr", "nlr", "ndl", "nud", "nup", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "ntd", "ntu", "nlr", "ncs", "nlt", "ntl", "nlt", "nup", "nud", "nrt", "ntd", "ntr", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "nul", "nrt", "nul", "ndr", "nul", "ndn", "ndr", "ntd", "nlt", "ndr", "ncs", "ntr", "ndn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nup", "ndr", "nlr", "nlr", "ntu", "nul", "ndr", "nul", "nud", "ndr", "nlr", "ntr", "nud", "nur", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ntr", "nrt", "ndl", "nur", "ntu", "nul", "ndr", "ntr", "nup", "ndr", "nul", "nud", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ntl", "nlr", "nul", "nrt", "nul", "ndr", "nul", "nup", "ndr", "ntr", "ndr", "nul", "ntl", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ntl", "nlr", "ntu", "nlr", "ntu", "nul", "ndr", "ndl", "nup", "ntl", "nul", "ndr", "ntr", "nup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ntd", "ndl", "nud", "ndn", "nud", "nrt", "ntr", "nur", "nlt", "ntl", "ndl", "nud", "nud", "ndn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "ntr", "ntl", "ntr", "ntl", "nlt", "ntl", "nlt", "ndr", "nul", "nud", "nud", "nur", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "ntd", "nul", "nur", "ntr", "ndr", "nul", "ndn", "nud", "ndn", "nud", "nur", "nlr", "glt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nud", "ndr", "ntu", "ndl", "nur", "ntr", "ndr", "ntr", "nur", "ntd", "ncs", "nlr", "ndl", "ndn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "ntd", "nul", "nud", "nur", "ndl", "nur", "ntr", "nur", "ndl", "ndn", "nud", "ndr", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nlr", "nlr", "nul", "nrt", "ntd", "nlt", "nur", "nlr", "nul", "nur", "ntd", "ntd", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"3", "5", "c", "8", "53"},
            {},
            {}
    };

    public static final String[][] CAVE_4 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ntu", "nlt", "ndr", "nlr", "nlr", "nlr", "nlr", "ndl", "nrt", "nlr", "ndl", "ndr", "nlr", "nlr", "nlr", "ntu", "nlr", "nlr", "nlt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nup", "ndr", "nul", "ndr", "ntu", "nlr", "ndl", "ntl", "nlr", "ndl", "nud", "nur", "nlr", "ndl", "ndn", "nur", "ndl", "ndr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "nul", "nrt", "nul", "nur", "ndl", "nud", "nur", "ndl", "nup", "nur", "ndl", "ndn", "nur", "ntr", "ndn", "nur", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nur", "nlr", "nlr", "nlr", "nlr", "nul", "nud", "ndr", "ntr", "ndr", "nlr", "nul", "ntl", "nlt", "nur", "ntd", "ndl", "ndn", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "nlr", "nlr", "ntu", "nlr", "nlt", "nud", "nud", "nup", "nur", "nlr", "ndl", "nur", "nlr", "ntu", "nlr", "ntd", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nur", "nlr", "ndl", "nur", "nlr", "nlr", "nul", "nud", "ndr", "nlr", "ndl", "nur", "nlr", "ndl", "nud", "ndn", "ndr", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "ntu", "ndl", "nur", "nlr", "nlr", "ndl", "ndn", "nud", "ntl", "nlt", "nur", "nlr", "ndl", "nud", "nur", "ntr", "nur", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nud", "nup", "ndr", "nlr", "nlr", "nul", "nud", "nur", "nul", "ndr", "nlr", "ndl", "nud", "nud", "nrt", "ntd", "nlr", "nlt", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nur", "nlr", "nul", "ndr", "nlr", "nlr", "ntr", "ndr", "nlr", "nul", "ndn", "nur", "ntr", "nur", "nlr", "nlr", "nlr", "ndl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "ndl", "ndr", "nul", "nrt", "ndl", "nud", "nur", "nlr", "ndl", "nur", "ndl", "nur", "nlr", "nlr", "nlr", "ndl", "nur", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nup", "ntl", "nul", "nrt", "ntu", "ntr", "nur", "ntu", "ndl", "nur", "ndl", "ntl", "ndl", "ndr", "nlr", "ndl", "nud", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ndl", "nur", "nlr", "nlr", "nul", "nur", "ndl", "nud", "nur", "ndl", "nud", "nup", "nur", "ntd", "ndl", "nur", "nul", "nud", "ndn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndn", "nud", "ndr", "ndl", "ndr", "ndl", "nrt", "nul", "nud", "ndr", "nul", "nur", "nlr", "nlr", "ndl", "nur", "nlr", "ndl", "nud", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nur", "nul", "nur", "nul", "nud", "ndr", "nlr", "nul", "nud", "ndr", "nlt", "ndr", "nlr", "nul", "nrt", "ndl", "nud", "nur", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "ntu", "nlr", "nlt", "ndr", "nul", "nud", "ndr", "ndl", "ntl", "nul", "ndr", "nul", "nrt", "ntu", "ndl", "nud", "nud", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nur", "ndl", "ndr", "nul", "ndr", "nul", "nud", "nur", "nul", "ndr", "nul", "nrt", "ntu", "nul", "nur", "nul", "nud", "nud", "ndn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ndl", "nud", "nud", "ndr", "ntd", "ndl", "nud", "ndn", "ndr", "nul", "ndr", "ndl", "nur", "ndl", "ndr", "ndl", "nud", "nud", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nul", "nur", "nul", "nud", "ndr", "nul", "nur", "ntr", "nur", "nlr", "nul", "nur", "ndl", "nur", "nul", "nur", "nul", "nud", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "nlr", "ndl", "nup", "nud", "ndr", "nlt", "nud", "nrt", "ntu", "ndl", "ndr", "nul", "ndr", "nlr", "nlr", "ndl", "nur", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nul", "nrt", "ntd", "nlr", "nul", "nur", "nlr", "ntd", "nlr", "gul", "nup", "nur", "nlr", "ntd", "nlr", "nlt", "nur", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"12", "3", "c", "20", "224"},
            {},
            {}
    };
    public static final String[][] SNOW_1 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "slr", "slr", "slr", "glt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"3", "3", "s", "100", "1"},
            {},
            {}
    };
    public static final String[][] SNOW_2 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "nlr", "nlr", "sdl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "sdl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "grt", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sur", "nlr", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"3", "3", "s", "100", "7"},
            {},
            {}
    };
    public static final String[][] SNOW_3 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ntu", "nlr", "nlr", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nup", "nur", "nlr", "nlr", "nlr", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ntu", "ntu", "slr", "nlr", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nud", "nur", "nlr", "nlr", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "gud", "ntl", "nlr", "nlr", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ntl", "nlr", "slr", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ntd", "tlr", "nlr", "nlr", "sul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"5", "3", "s", "100", "11"},
            {},
            {"5,9"}
    };
    public static final String[][] DESERT_1 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "ndl", "ndr", "nlr", "ndl", "ndr", "ndl", "ndr", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nul", "nud", "ndr", "nul", "nud", "nur", "nul", "nrt", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "nul", "nur", "ntu", "ntr", "fdn", "ndr", "ndl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nur", "flt", "ndr", "nul", "nup", "nur", "nul", "nud", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "ntu", "nul", "ndr", "nlr", "ndl", "ndn", "nud", "gup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "nul", "nur", "ndl", "ntl", "ndl", "nur", "nul", "nur", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "nlr", "nlt", "nud", "nud", "nur", "nlr", "nlr", "ndl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "ndl", "nur", "ncs", "nlr", "nlt", "ndr", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nud", "nur", "ndl", "nud", "ndr", "ndl", "nur", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nul", "frt", "nul", "nur", "nul", "nur", "nlr", "nlr", "nlt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"12", "12", "d", "3", "31"},
            {},
            {}
    };
    public static final String[][] DESERT_2 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "ntu", "ndl", "ndr", "nlr", "ntu", "ntu", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ndl", "nup", "nur", "nul", "nrt", "nul", "ntl", "nlt", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ntd", "nlt", "ndn", "nrt", "ndl", "nrt", "nul", "ndr", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "ntu", "nlt", "nur", "ndl", "nud", "ndr", "nlt", "nud", "nup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nur", "nlr", "ndl", "nud", "nur", "ntd", "ndl", "nur", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nrt", "nlr", "nul", "nud", "ndr", "nlr", "ntr", "ndn", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nlr", "ndl", "ndr", "nul", "nud", "ndn", "nur", "ntr", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ntu", "ntd", "nul", "ndr", "nul", "ntl", "ndl", "nud", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nup", "ntl", "ndl", "ndn", "nud", "nrt", "ntr", "ntl", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "nul", "nur", "nul", "nur", "glt", "nup", "nur", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"7", "3", "d", "3", "32"},
            {},
            {}
    };
    public static final String[][] LEVEL_D = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "prt", "ilr", "nlr", "tlr", "plr", "glt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"5", "3", "f", "100", "3"},
            {"3,3", "7,3"},
            {"4,3", "6,3"}
    };
    public static final String[][] CAVE_5 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndn", "srt", "nlr", "ntu", "nlt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "ntu", "ncs", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nul", "nup", "nud", "sup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "nlr", "ntd", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nlr", "glt", "frt", "sul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"3", "3", "c", "2", "30"},
            {},
            {}
    };
    public static final String[][] CAVE_6 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "ntu", "ndl", "ndr", "ndl", "ndr", "glt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nul", "ttl", "nul", "nup", "nud", "sdn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ndl", "nur", "ndl", "nrt", "ntr", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndn", "nud", "ndr", "nul", "sdn", "nud", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nud", "nur", "ndl", "nud", "ttl", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "nul", "sdn", "ntl", "nul", "ntl", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nlr", "nul", "nur", "nlr", "nul", "nup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"3", "6", "c", "100", "51"},
            {},
            {"5,4", "8,7"}
    };
    public static final String[][] DESERT_3 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "srt", "nlr", "ntu", "nlr", "nlr", "nlr", "nlt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "ncs", "nlr", "nlr", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "nlt", "nur", "nlr", "nlr", "ndl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ndl", "ndr", "nlr", "nlr", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "srt", "nul", "nur", "nlr", "nlr", "ndl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "grt", "nlr", "nlr", "nlr", "mtu", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "srt", "nlr", "nlr", "nlr", "nul", "wrt", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"9", "3", "d", "100", "71"},
            {},
            {}
    };
    public static final String[][] SNOW_4 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ndl", "ndr", "ndl", "srt", "ntu", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "ntd", "ncs", "ncs", "ndl", "ntl", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "gup", "ndr", "ncs", "ncs", "ncs", "ncs", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ncs", "ncs", "ncs", "ncs", "ncs", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ncs", "ncs", "ncs", "ncs", "ntr", "sdn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ntr", "nur", "ncs", "ncs", "jcd", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ntd", "slt", "nur", "nul", "nur", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"6", "6", "s", "100", "33"},
            {},
            {}
    };
    public static final String[][] one = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "ntu", "ndl", "sdn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "sdn", "nud", "nur", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ntr", "nur", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sdn", "nud", "ndr", "ndl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nul", "gup", "nur", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"5", "3", "f", "4", "38"},
            {},
            {}
    };
    public static final String[][] two = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "srt", "nlr", "nlr", "ntu", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "ndl", "nud", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "prt", "ntd", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "mtl", "ndl", "prt", "ndl", "sup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sup", "nur", "glt", "nur", "wlt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"5", "3", "f", "4", "34"},
            {"4,5", "5,6"},
            {}
    };
    public static final String[][] three = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "nlr", "ndl", "ndr", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "ndl", "nud", "sup", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "mtl", "nul", "nud", "nur", "nlr", "ntd", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "sdn", "nur", "nlr", "ndl", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ntl", "ndl", "ndr", "nul", "nur", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nul", "nud", "nud", "ndr", "wlt", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "srt", "nlr", "nul", "gup", "nur", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"6", "3", "f", "8", "80"},
            {},
            {}
    };
    public static final String[][] four = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndn", "ndr", "ntu", "nlr", "nlr", "nlr", "nlt", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "nul", "nur", "nlr", "nlt", "ndr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "ctu", "nlt", "ndr", "ntr", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "nul", "nur", "nlr", "ntr", "nud", "sup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nlr", "nlr", "ndl", "nud", "nur", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ndl", "ndr", "nul", "nur", "slt", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sup", "nur", "nul", "grt", "nlr", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"6", "3", "f", "6", "58"},
            {},
            {}
    };
    public static final String[][] five = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "nlr", "ntu", "nlt", "nrt", "nlr", "ntu", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndn", "ndr", "ntd", "nlr", "nlr", "nlr", "ntd", "ndl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "ntr", "ndr", "nlr", "ntu", "nlr", "nlt", "ntl", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nup", "nud", "ndr", "ntd", "ndl", "ndn", "nud", "nup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sup", "ndr", "ntr", "nud", "gdn", "nud", "ntl", "nul", "sdn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndn", "nud", "nup", "sup", "nur", "nul", "nud", "ndn", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "ntr", "nrt", "nlr", "ntu", "nlr", "nul", "ntl", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nur", "ntu", "nlr", "ntd", "nlr", "ntu", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nlr", "ntd", "nlr", "nlt", "nrt", "ntd", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"3", "3", "f", "7", "64"},
            {},
            {}
    };
    public static final String[][] six = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ndl", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nul", "sup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "nul", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sup", "nur", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "glt", "sup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"3", "3", "f", "4", "34"},
            {},
            {}
    };
    public static final String[][] seven = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "srt", "nlr", "ntu", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "nul", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "nlr", "ndl", "nud", "ndn", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "nlt", "nud", "nur", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndr", "nul", "srt", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nud", "ndr", "nlr", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nur", "nul", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ndl", "ndn", "nur", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "sdn", "ntl", "ntd", "nlt", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nul", "grt", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"5", "3", "f", "7", "65"},
            {},
            {}
    };
    public static final String[][] eight = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nrt", "ntu", "ndl", "nrt", "nlr", "nlr", "ndl", "ndr", "ntu", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "ntr", "nur", "nlr", "nlr", "ndl", "nud", "nud", "nud", "nup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "nup", "ndr", "nlr", "ndl", "ntl", "nul", "nud", "nur", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "nlr", "nul", "ndr", "nul", "nud", "ndr", "ntd", "nlt", "nud", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndr", "nlr", "nlr", "nul", "srt", "nul", "nud", "ndr", "nlr", "ntr", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ntl", "nlr", "nlr", "ndl", "ndr", "nlr", "nul", "nur", "ndl", "nup", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nud", "ndn", "ndr", "nul", "nud", "srt", "nlr", "ndl", "nur", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ntr", "nur", "nlr", "ntd", "nlr", "nlr", "nul", "ndr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "ndn", "nud", "ndr", "ndl", "sdn", "ndr", "nlr", "ndl", "nur", "ndl", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "nur", "ntd", "nul", "nur", "nul", "gup", "nrt", "ntd", "nlr", "nul", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"6", "3", "f", "11", "109"},
            {},
            {}
    };
    public static final String[][][] LEVEL = {
            FOREST_1, FOREST_2, FOREST_3, FOREST_4, FOREST_5, FOREST_6, FOREST_7, CAVE_1, SNOW_1, SNOW_2, DESERT_1, DESERT_2, LEVEL_D, CAVE_2, CAVE_3, CAVE_4, FOREST_1, FOREST_2, FOREST_3, FOREST_4, FOREST_5, FOREST_6, FOREST_7, CAVE_1, SNOW_1, SNOW_2, DESERT_1, DESERT_2, LEVEL_D, CAVE_2, CAVE_3, CAVE_4
    };
    public static final String[][][] WORLD_1 = {
            FOREST_1, FOREST_2, FOREST_3, FOREST_4, FOREST_5, FOREST_6, FOREST_7, one, six, two, four, three, seven, five, eight
    };
    public static final String[][][] WORLD_2 = {
            CAVE_5, CAVE_1, CAVE_6, CAVE_2, CAVE_3, CAVE_4
    };
    public static final String[][][] WORLD_3 = {
            SNOW_1, SNOW_2, SNOW_3, SNOW_4
    };
    public static final String[][][] WORLD_4 = {
            DESERT_3, DESERT_1, DESERT_2
    };
    public static final String[][][] TEST_LEVEL = {
            one, two, three, four, five, six, seven, eight
    };
    public static final String[][][][] WORLDS = {
            WORLD_1, WORLD_2, WORLD_3, WORLD_4
    };
    public static final String[][] TEMPLATE_3x3 = {
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
            {},
            {}
    };
    public static final String[][] TEMPLATE_5x5 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"0", "0", "f", "100", "100"},
            {},
            {}
    };
    public static final String[][] TEMPLATE_7x7 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"0", "0", "f", "100", "100"},
            {},
            {}
    };
    public static final String[][] TEMPLATE_10x10 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"0", "0", "f", "100", "100"},
            {},
            {}
    };
    public static final String[][] TEMPLATE_20x20 = {
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx", "xxx"},
            {"0", "0", "f", "100", "100"},
            {},
            {}
    };
}
