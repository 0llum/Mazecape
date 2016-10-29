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
    public static final String[] BLOOD = {
            "xup", "xrt", "xdn", "xlt", "xud", "xlr", "xur", "xdr", "xdl", "xul", "xtu", "xtr", "xtd", "xtl", "xcs"
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
    public static final String[] DIARY = {
            "dup", "drt", "ddn", "dlt", "dud", "dlr", "dur", "ddr", "ddl", "dul", "dtu", "dtr", "dtd", "dtl", "dcs"
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
    public static final String[] BRIDGE = {
            "bup", "brt", "bdn", "blt", "bud", "blr", "bur", "bdr", "bdl", "bul", "btu", "btr", "btd", "btl", "bcs"
    };
    public static final String[] BRIDGE_HOLE = {
            "qup", "qrt", "qdn", "qlt", "qud", "qlr", "qur", "qdr", "qdl", "qul", "qtu", "qtr", "qtd", "qtl", "qcs"
    };
    public static final String[] CRACK = {
            "cup", "crt", "cdn", "clt", "cud", "clr", "cur", "cdr", "cdl", "cul", "ctu", "ctr", "ctd", "ctl", "ccs"
    };
    public static final String[] HOLE = {
            "hup", "hrt", "hdn", "hlt", "hud", "hlr", "hur", "hdr", "hdl", "hul", "htu", "htr", "htd", "htl", "hcs"
    };
    public static final String[] LOOKOUT = {
            "lup", "lrt", "ldn", "llt", "lud", "llr", "lur", "ldr", "ldl", "lul", "ltu", "ltr", "ltd", "ltl", "lcs"
    };
    public static final String[] FOG = {
            "vup", "vrt", "vdn", "vlt", "vud", "vlr", "vur", "vdr", "vdl", "vul", "vtu", "vtr", "vtd", "vtl", "vcs"
    };
    public static final String[] JUMP = {
            "jup", "jrt", "jdn", "jlt", "jtl", "jtr", "jtu", "jtd", "jur", "jru", "jrd", "jdr", "jld", "jdl", "jlu", "jul", "jcd", "jcl", "jcu", "jcr"
    };
    public static final String[] ROTATION = {
            "yup", "yrt", "ydn", "ylt", "yud", "ylr", "yur", "ydr", "ydl", "yul", "ytu", "ytr", "ytd", "ytl", "ycs"
    };
    public static final String[] SWITCH = {
            "zup", "zrt", "zdn", "zlt", "zud", "zlr", "zur", "zdr", "zdl", "zul", "ztu", "ztr", "ztd", "ztl", "zcs"
    };
    public static final String[][] TILES = {
            NORMAL, GOAL, STAR, FIRE, PORTAL, MONSTER, WEAPON, CRACK, TRAP_ACTIVE, SWITCH, ROTATION, FOG, LOOKOUT
    };
}
