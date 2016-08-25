package com.ollum.mazecape.Arrays;

public class Tiles {
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
}
