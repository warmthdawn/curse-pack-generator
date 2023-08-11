import org.json.JSONArray
import org.json.JSONObject

// {
//  "minecraft": {
//    "version": "1.12.2",
//    "modLoaders": [
//      {
//        "id": "forge-14.23.5.2860",
//        "primary": true
//      }
//    ]
//  },
//  "manifestType": "minecraftModpack",
//  "manifestVersion": 1,
//  "name": "Multiblock Madness",
//  "version": "3.0.5",
//  "author": "gtjoe51",
//  "files": [
//    {
//      "projectID": 260262,
//      "fileID": 3459767,
//      "required": true
//    },
//    {
//      "projectID": 225635,
//      "fileID": 3317747,
//      "required": true
//    },
//    {
//      "projectID": 225643,
//      "fileID": 3330934,
//      "required": true
//    },
//    {
//      "projectID": 245755,
//      "fileID": 2859589,
//      "required": true
//    },
//    {
//      "projectID": 229002,
//      "fileID": 2735197,
//      "required": true
//    },
//    {
//      "projectID": 224535,
//      "fileID": 2443253,
//      "required": true
//    },
//    {
//      "projectID": 282623,
//      "fileID": 3280750,
//      "required": true
//    },
//    {
//      "projectID": 408853,
//      "fileID": 3608582,
//      "required": true
//    },
//    {
//      "projectID": 513857,
//      "fileID": 3739783,
//      "required": true
//    },
//    {
//      "projectID": 248020,
//      "fileID": 3178199,
//      "required": true
//    },
//    {
//      "projectID": 322861,
//      "fileID": 3214400,
//      "required": true
//    },
//    {
//      "projectID": 277064,
//      "fileID": 3483816,
//      "required": true
//    },
//    {
//      "projectID": 251389,
//      "fileID": 3159511,
//      "required": true
//    },
//    {
//      "projectID": 284904,
//      "fileID": 2568865,
//      "required": true
//    },
//    {
//      "projectID": 265917,
//      "fileID": 2951731,
//      "required": true
//    },
//    {
//      "projectID": 251396,
//      "fileID": 3317119,
//      "required": true
//    },
//    {
//      "projectID": 250290,
//      "fileID": 2665849,
//      "required": true
//    },
//    {
//      "projectID": 238003,
//      "fileID": 2559089,
//      "required": true
//    },
//    {
//      "projectID": 74924,
//      "fileID": 2713386,
//      "required": true
//    },
//    {
//      "projectID": 308380,
//      "fileID": 3614585,
//      "required": true
//    },
//    {
//      "projectID": 313970,
//      "fileID": 2906487,
//      "required": true
//    },
//    {
//      "projectID": 380998,
//      "fileID": 3132891,
//      "required": true
//    },
//    {
//      "projectID": 233564,
//      "fileID": 2966851,
//      "required": true
//    },
//    {
//      "projectID": 272671,
//      "fileID": 2843439,
//      "required": true
//    },
//    {
//      "projectID": 224641,
//      "fileID": 2861573,
//      "required": true
//    },
//    {
//      "projectID": 314002,
//      "fileID": 2719400,
//      "required": true
//    },
//    {
//      "projectID": 70496,
//      "fileID": 2518031,
//      "required": true
//    },
//    {
//      "projectID": 292785,
//      "fileID": 2915154,
//      "required": true
//    },
//    {
//      "projectID": 293905,
//      "fileID": 2565793,
//      "required": true
//    },
//    {
//      "projectID": 280506,
//      "fileID": 2899375,
//      "required": true
//    },
//    {
//      "projectID": 246996,
//      "fileID": 3440963,
//      "required": true
//    },
//    {
//      "projectID": 280510,
//      "fileID": 2643711,
//      "required": true
//    },
//    {
//      "projectID": 270457,
//      "fileID": 2918455,
//      "required": true
//    },
//    {
//      "projectID": 362056,
//      "fileID": 3662442,
//      "required": true
//    },
//    {
//      "projectID": 247007,
//      "fileID": 3053017,
//      "required": true
//    },
//    {
//      "projectID": 250363,
//      "fileID": 2746011,
//      "required": true
//    },
//    {
//      "projectID": 239197,
//      "fileID": 3642364,
//      "required": true
//    },
//    {
//      "projectID": 231382,
//      "fileID": 3408276,
//      "required": true
//    },
//    {
//      "projectID": 268250,
//      "fileID": 3382321,
//      "required": true
//    },
//    {
//      "projectID": 281657,
//      "fileID": 3474317,
//      "required": true
//    },
//    {
//      "projectID": 299540,
//      "fileID": 2706111,
//      "required": true
//    },
//    {
//      "projectID": 291727,
//      "fileID": 2549332,
//      "required": true
//    },
//    {
//      "projectID": 250398,
//      "fileID": 3025548,
//      "required": true
//    },
//    {
//      "projectID": 316304,
//      "fileID": 2687139,
//      "required": true
//    },
//    {
//      "projectID": 304022,
//      "fileID": 2830253,
//      "required": true
//    },
//    {
//      "projectID": 304024,
//      "fileID": 2830114,
//      "required": true
//    },
//    {
//      "projectID": 243707,
//      "fileID": 3644727,
//      "required": true
//    },
//    {
//      "projectID": 367706,
//      "fileID": 3671340,
//      "required": true
//    },
//    {
//      "projectID": 402338,
//      "fileID": 3373954,
//      "required": true
//    },
//    {
//      "projectID": 244844,
//      "fileID": 3505633,
//      "required": true
//    },
//    {
//      "projectID": 223622,
//      "fileID": 2516219,
//      "required": true
//    },
//    {
//      "projectID": 295114,
//      "fileID": 3092102,
//      "required": true
//    },
//    {
//      "projectID": 232564,
//      "fileID": 3280205,
//      "required": true
//    },
//    {
//      "projectID": 223628,
//      "fileID": 2629023,
//      "required": true
//    },
//    {
//      "projectID": 585546,
//      "fileID": 3707413,
//      "required": true
//    },
//    {
//      "projectID": 493962,
//      "fileID": 3577829,
//      "required": true
//    },
//    {
//      "projectID": 239286,
//      "fileID": 3722420,
//      "required": true
//    },
//    {
//      "projectID": 291786,
//      "fileID": 2701203,
//      "required": true
//    },
//    {
//      "projectID": 278385,
//      "fileID": 2482543,
//      "required": true
//    },
//    {
//      "projectID": 247111,
//      "fileID": 2887479,
//      "required": true
//    },
//    {
//      "projectID": 545353,
//      "fileID": 3519479,
//      "required": true
//    },
//    {
//      "projectID": 237065,
//      "fileID": 3331364,
//      "required": true
//    },
//    {
//      "projectID": 231484,
//      "fileID": 2862651,
//      "required": true
//    },
//    {
//      "projectID": 223666,
//      "fileID": 2915506,
//      "required": true
//    },
//    {
//      "projectID": 300742,
//      "fileID": 3006392,
//      "required": true
//    },
//    {
//      "projectID": 261648,
//      "fileID": 2484098,
//      "required": true
//    },
//    {
//      "projectID": 224791,
//      "fileID": 2822288,
//      "required": true
//    },
//    {
//      "projectID": 250498,
//      "fileID": 3048685,
//      "required": true
//    },
//    {
//      "projectID": 311938,
//      "fileID": 2683267,
//      "required": true
//    },
//    {
//      "projectID": 237102,
//      "fileID": 3157548,
//      "required": true
//    },
//    {
//      "projectID": 238222,
//      "fileID": 3040523,
//      "required": true
//    },
//    {
//      "projectID": 300777,
//      "fileID": 3695248,
//      "required": true
//    },
//    {
//      "projectID": 271740,
//      "fileID": 2707353,
//      "required": true
//    },
//    {
//      "projectID": 253874,
//      "fileID": 2989594,
//      "required": true
//    },
//    {
//      "projectID": 349942,
//      "fileID": 3008019,
//      "required": true
//    },
//    {
//      "projectID": 225957,
//      "fileID": 2499443,
//      "required": true
//    },
//    {
//      "projectID": 227083,
//      "fileID": 2518667,
//      "required": true
//    },
//    {
//      "projectID": 282940,
//      "fileID": 3460961,
//      "required": true
//    },
//    {
//      "projectID": 309756,
//      "fileID": 3328809,
//      "required": true
//    },
//    {
//      "projectID": 74072,
//      "fileID": 2902483,
//      "required": true
//    },
//    {
//      "projectID": 410295,
//      "fileID": 3593191,
//      "required": true
//    },
//    {
//      "projectID": 247217,
//      "fileID": 2740774,
//      "required": true
//    },
//    {
//      "projectID": 278494,
//      "fileID": 3327893,
//      "required": true
//    },
//    {
//      "projectID": 237167,
//      "fileID": 2985811,
//      "required": true
//    },
//    {
//      "projectID": 250577,
//      "fileID": 2648656,
//      "required": true
//    },
//    {
//      "projectID": 275153,
//      "fileID": 2486198,
//      "required": true
//    },
//    {
//      "projectID": 408089,
//      "fileID": 3479639,
//      "required": true
//    },
//    {
//      "projectID": 282988,
//      "fileID": 2598021,
//      "required": true
//    },
//    {
//      "projectID": 230497,
//      "fileID": 2450900,
//      "required": true
//    },
//    {
//      "projectID": 74120,
//      "fileID": 2711439,
//      "required": true
//    },
//    {
//      "projectID": 258426,
//      "fileID": 2755790,
//      "required": true
//    },
//    {
//      "projectID": 291936,
//      "fileID": 2682853,
//      "required": true
//    },
//    {
//      "projectID": 423743,
//      "fileID": 3272319,
//      "required": true
//    },
//    {
//      "projectID": 271835,
//      "fileID": 2920437,
//      "required": true
//    },
//    {
//      "projectID": 252848,
//      "fileID": 2893527,
//      "required": true
//    },
//    {
//      "projectID": 419286,
//      "fileID": 3687785,
//      "required": true
//    },
//    {
//      "projectID": 423758,
//      "fileID": 3626196,
//      "required": true
//    },
//    {
//      "projectID": 303122,
//      "fileID": 2919936,
//      "required": true
//    },
//    {
//      "projectID": 268495,
//      "fileID": 3655827,
//      "required": true
//    },
//    {
//      "projectID": 232758,
//      "fileID": 3159497,
//      "required": true
//    },
//    {
//      "projectID": 242818,
//      "fileID": 2779848,
//      "required": true
//    },
//    {
//      "projectID": 241721,
//      "fileID": 3044416,
//      "required": true
//    },
//    {
//      "projectID": 391401,
//      "fileID": 3613142,
//      "required": true
//    },
//    {
//      "projectID": 223852,
//      "fileID": 2952606,
//      "required": true
//    },
//    {
//      "projectID": 256247,
//      "fileID": 2704832,
//      "required": true
//    },
//    {
//      "projectID": 232791,
//      "fileID": 3330021,
//      "required": true
//    },
//    {
//      "projectID": 293110,
//      "fileID": 2781748,
//      "required": true
//    },
//    {
//      "projectID": 289760,
//      "fileID": 3748305,
//      "required": true
//    },
//    {
//      "projectID": 290881,
//      "fileID": 2545640,
//      "required": true
//    },
//    {
//      "projectID": 527688,
//      "fileID": 3460723,
//      "required": true
//    },
//    {
//      "projectID": 376903,
//      "fileID": 3648592,
//      "required": true
//    },
//    {
//      "projectID": 270789,
//      "fileID": 2920436,
//      "required": true
//    },
//    {
//      "projectID": 240630,
//      "fileID": 2728585,
//      "required": true
//    },
//    {
//      "projectID": 270790,
//      "fileID": 2713714,
//      "required": true
//    },
//    {
//      "projectID": 254035,
//      "fileID": 2571132,
//      "required": true
//    },
//    {
//      "projectID": 248453,
//      "fileID": 2785465,
//      "required": true
//    },
//    {
//      "projectID": 268560,
//      "fileID": 2835175,
//      "required": true
//    },
//    {
//      "projectID": 238403,
//      "fileID": 2443194,
//      "required": true
//    },
//    {
//      "projectID": 268566,
//      "fileID": 2835177,
//      "required": true
//    },
//    {
//      "projectID": 223891,
//      "fileID": 2526674,
//      "required": true
//    },
//    {
//      "projectID": 223896,
//      "fileID": 2680892,
//      "required": true
//    },
//    {
//      "projectID": 383632,
//      "fileID": 3056455,
//      "required": true
//    },
//    {
//      "projectID": 237307,
//      "fileID": 2937869,
//      "required": true
//    },
//    {
//      "projectID": 312149,
//      "fileID": 2800346,
//      "required": true
//    },
//    {
//      "projectID": 223908,
//      "fileID": 3168678,
//      "required": true
//    },
//    {
//      "projectID": 497576,
//      "fileID": 3468175,
//      "required": true
//    },
//    {
//      "projectID": 284229,
//      "fileID": 2968355,
//      "required": true
//    },
//    {
//      "projectID": 244023,
//      "fileID": 2463886,
//      "required": true
//    },
//    {
//      "projectID": 229503,
//      "fileID": 3102626,
//      "required": true
//    },
//    {
//      "projectID": 390354,
//      "fileID": 3411757,
//      "required": true
//    },
//    {
//      "projectID": 304346,
//      "fileID": 3328808,
//      "required": true
//    },
//    {
//      "projectID": 336748,
//      "fileID": 3218640,
//      "required": true
//    },
//    {
//      "projectID": 228404,
//      "fileID": 3117927,
//      "required": true
//    },
//    {
//      "projectID": 235107,
//      "fileID": 2695044,
//      "required": true
//    },
//    {
//      "projectID": 247401,
//      "fileID": 3611193,
//      "required": true
//    },
//    {
//      "projectID": 255221,
//      "fileID": 2915872,
//      "required": true
//    },
//    {
//      "projectID": 261924,
//      "fileID": 2733508,
//      "required": true
//    },
//    {
//      "projectID": 285385,
//      "fileID": 2634368,
//      "required": true
//    },
//    {
//      "projectID": 245174,
//      "fileID": 2755787,
//      "required": true
//    },
//    {
//      "projectID": 255232,
//      "fileID": 2830265,
//      "required": true
//    },
//    {
//      "projectID": 275356,
//      "fileID": 2468740,
//      "required": true
//    },
//    {
//      "projectID": 255257,
//      "fileID": 3507866,
//      "required": true
//    },
//    {
//      "projectID": 245211,
//      "fileID": 2667280,
//      "required": true
//    },
//    {
//      "projectID": 222880,
//      "fileID": 2926428,
//      "required": true
//    },
//    {
//      "projectID": 297720,
//      "fileID": 2708711,
//      "required": true
//    },
//    {
//      "projectID": 277616,
//      "fileID": 2959847,
//      "required": true
//    },
//    {
//      "projectID": 253043,
//      "fileID": 3597087,
//      "required": true
//    },
//    {
//      "projectID": 287683,
//      "fileID": 3174535,
//      "required": true
//    },
//    {
//      "projectID": 277631,
//      "fileID": 2750633,
//      "required": true
//    },
//    {
//      "projectID": 59816,
//      "fileID": 2905241,
//      "required": true
//    },
//    {
//      "projectID": 398267,
//      "fileID": 3613140,
//      "required": true
//    },
//    {
//      "projectID": 264231,
//      "fileID": 2939529,
//      "required": true
//    },
//    {
//      "projectID": 236307,
//      "fileID": 3159505,
//      "required": true
//    },
//    {
//      "projectID": 345779,
//      "fileID": 3299037,
//      "required": true
//    },
//    {
//      "projectID": 284350,
//      "fileID": 3533386,
//      "required": true
//    },
//    {
//      "projectID": 284351,
//      "fileID": 3205284,
//      "required": true
//    },
//    {
//      "projectID": 267602,
//      "fileID": 2915363,
//      "required": true
//    },
//    {
//      "projectID": 296646,
//      "fileID": 2576768,
//      "required": true
//    },
//    {
//      "projectID": 322344,
//      "fileID": 3509087,
//      "required": true
//    },
//    {
//      "projectID": 251974,
//      "fileID": 3449984,
//      "required": true
//    },
//    {
//      "projectID": 231868,
//      "fileID": 2972849,
//      "required": true
//    },
//    {
//      "projectID": 322347,
//      "fileID": 3254160,
//      "required": true
//    },
//    {
//      "projectID": 260912,
//      "fileID": 2745852,
//      "required": true
//    },
//    {
//      "projectID": 285492,
//      "fileID": 2705304,
//      "required": true
//    },
//    {
//      "projectID": 228525,
//      "fileID": 2836960,
//      "required": true
//    },
//    {
//      "projectID": 245287,
//      "fileID": 2658176,
//      "required": true
//    },
//    {
//      "projectID": 278799,
//      "fileID": 2833002,
//      "required": true
//    },
//    {
//      "projectID": 244181,
//      "fileID": 2509046,
//      "required": true
//    },
//    {
//      "projectID": 225194,
//      "fileID": 2491032,
//      "required": true
//    },
//    {
//      "projectID": 336895,
//      "fileID": 3386487,
//      "required": true
//    },
//    {
//      "projectID": 229664,
//      "fileID": 2888473,
//      "required": true
//    },
//    {
//      "projectID": 254241,
//      "fileID": 2656683,
//      "required": true
//    },
//    {
//      "projectID": 233019,
//      "fileID": 2450734,
//      "required": true
//    },
//    {
//      "projectID": 317915,
//      "fileID": 3206298,
//      "required": true
//    },
//    {
//      "projectID": 250898,
//      "fileID": 2897369,
//      "required": true
//    },
//    {
//      "projectID": 227443,
//      "fileID": 2920505,
//      "required": true
//    },
//    {
//      "projectID": 271009,
//      "fileID": 2863246,
//      "required": true
//    },
//    {
//      "projectID": 288885,
//      "fileID": 2838777,
//      "required": true
//    },
//    {
//      "projectID": 266550,
//      "fileID": 3270468,
//      "required": true
//    },
//    {
//      "projectID": 291126,
//      "fileID": 3023901,
//      "required": true
//    },
//    {
//      "projectID": 235279,
//      "fileID": 2915375,
//      "required": true
//    },
//    {
//      "projectID": 306770,
//      "fileID": 3162874,
//      "required": true
//    },
//    {
//      "projectID": 223008,
//      "fileID": 2828357,
//      "required": true
//    },
//    {
//      "projectID": 298965,
//      "fileID": 3374254,
//      "required": true
//    },
//    {
//      "projectID": 243121,
//      "fileID": 2924091,
//      "required": true
//    },
//    {
//      "projectID": 231951,
//      "fileID": 2974106,
//      "required": true
//    },
//    {
//      "projectID": 233071,
//      "fileID": 2562139,
//      "required": true
//    },
//    {
//      "projectID": 264353,
//      "fileID": 2508268,
//      "required": true
//    },
//    {
//      "projectID": 250957,
//      "fileID": 2630860,
//      "required": true
//    },
//    {
//      "projectID": 244258,
//      "fileID": 3268501,
//      "required": true
//    },
//    {
//      "projectID": 75598,
//      "fileID": 2957213,
//      "required": true
//    },
//    {
//      "projectID": 296769,
//      "fileID": 2576706,
//      "required": true
//    },
//    {
//      "projectID": 314645,
//      "fileID": 2951672,
//      "required": true
//    },
//    {
//      "projectID": 235338,
//      "fileID": 3485545,
//      "required": true
//    },
//    {
//      "projectID": 253211,
//      "fileID": 3308160,
//      "required": true
//    },
//    {
//      "projectID": 233105,
//      "fileID": 2745846,
//      "required": true
//    },
//    {
//      "projectID": 377201,
//      "fileID": 3140670,
//      "required": true
//    },
//    {
//      "projectID": 288959,
//      "fileID": 2535057,
//      "required": true
//    },
//    {
//      "projectID": 274440,
//      "fileID": 2497317,
//      "required": true
//    },
//    {
//      "projectID": 285612,
//      "fileID": 3133651,
//      "required": true
//    },
//    {
//      "projectID": 253230,
//      "fileID": 2465617,
//      "required": true
//    },
//    {
//      "projectID": 570458,
//      "fileID": 3755096,
//      "required": true
//    },
//    {
//      "projectID": 306851,
//      "fileID": 2638208,
//      "required": true
//    },
//    {
//      "projectID": 246550,
//      "fileID": 3576641,
//      "required": true
//    },
//    {
//      "projectID": 223094,
//      "fileID": 2482481,
//      "required": true
//    },
//    {
//      "projectID": 248787,
//      "fileID": 2987247,
//      "required": true
//    },
//    {
//      "projectID": 226447,
//      "fileID": 2477566,
//      "required": true
//    },
//    {
//      "projectID": 224218,
//      "fileID": 2707509,
//      "required": true
//    },
//    {
//      "projectID": 238747,
//      "fileID": 2739582,
//      "required": true
//    },
//    {
//      "projectID": 245453,
//      "fileID": 2691536,
//      "required": true
//    },
//    {
//      "projectID": 311357,
//      "fileID": 3057332,
//      "required": true
//    },
//    {
//      "projectID": 363856,
//      "fileID": 2886150,
//      "required": true
//    },
//    {
//      "projectID": 242106,
//      "fileID": 2539562,
//      "required": true
//    },
//    {
//      "projectID": 301310,
//      "fileID": 2863053,
//      "required": true
//    },
//    {
//      "projectID": 247695,
//      "fileID": 2755795,
//      "required": true
//    },
//    {
//      "projectID": 359407,
//      "fileID": 3658279,
//      "required": true
//    },
//    {
//      "projectID": 293505,
//      "fileID": 2766866,
//      "required": true
//    },
//    {
//      "projectID": 236541,
//      "fileID": 3611363,
//      "required": true
//    },
//    {
//      "projectID": 357178,
//      "fileID": 3076610,
//      "required": true
//    },
//    {
//      "projectID": 236542,
//      "fileID": 3587070,
//      "required": true
//    },
//    {
//      "projectID": 245480,
//      "fileID": 2691542,
//      "required": true
//    },
//    {
//      "projectID": 278993,
//      "fileID": 2846217,
//      "required": true
//    },
//    {
//      "projectID": 235440,
//      "fileID": 2715198,
//      "required": true
//    },
//    {
//      "projectID": 235442,
//      "fileID": 2743885,
//      "required": true
//    },
//    {
//      "projectID": 230976,
//      "fileID": 2463272,
//      "required": true
//    },
//    {
//      "projectID": 303584,
//      "fileID": 2621911,
//      "required": true
//    },
//    {
//      "projectID": 301356,
//      "fileID": 3091010,
//      "required": true
//    },
//    {
//      "projectID": 60089,
//      "fileID": 2671937,
//      "required": true
//    },
//    {
//      "projectID": 228756,
//      "fileID": 2747935,
//      "required": true
//    },
//    {
//      "projectID": 227639,
//      "fileID": 3051450,
//      "required": true
//    },
//    {
//      "projectID": 266736,
//      "fileID": 2668113,
//      "required": true
//    },
//    {
//      "projectID": 229876,
//      "fileID": 2483393,
//      "required": true
//    },
//    {
//      "projectID": 246640,
//      "fileID": 2704562,
//      "required": true
//    },
//    {
//      "projectID": 272335,
//      "fileID": 2645867,
//      "required": true
//    },
//    {
//      "projectID": 257814,
//      "fileID": 3626833,
//      "required": true
//    },
//    {
//      "projectID": 220954,
//      "fileID": 3488553,
//      "required": true
//    },
//    {
//      "projectID": 254466,
//      "fileID": 2498115,
//      "required": true
//    },
//    {
//      "projectID": 257818,
//      "fileID": 3584330,
//      "required": true
//    },
//    {
//      "projectID": 64578,
//      "fileID": 3328811,
//      "required": true
//    },
//    {
//      "projectID": 243298,
//      "fileID": 3116493,
//      "required": true
//    },
//    {
//      "projectID": 265642,
//      "fileID": 3679868,
//      "required": true
//    },
//    {
//      "projectID": 224320,
//      "fileID": 2977010,
//      "required": true
//    },
//    {
//      "projectID": 283525,
//      "fileID": 2947622,
//      "required": true
//    },
//    {
//      "projectID": 256717,
//      "fileID": 2666198,
//      "required": true
//    },
//    {
//      "projectID": 238856,
//      "fileID": 2950248,
//      "required": true
//    },
//    {
//      "projectID": 238857,
//      "fileID": 2863771,
//      "required": true
//    },
//    {
//      "projectID": 266784,
//      "fileID": 3146549,
//      "required": true
//    },
//    {
//      "projectID": 228815,
//      "fileID": 2699055,
//      "required": true
//    },
//    {
//      "projectID": 228816,
//      "fileID": 2699056,
//      "required": true
//    },
//    {
//      "projectID": 293602,
//      "fileID": 2925154,
//      "required": true
//    },
//    {
//      "projectID": 242223,
//      "fileID": 3524685,
//      "required": true
//    },
//    {
//      "projectID": 228823,
//      "fileID": 2904183,
//      "required": true
//    },
//    {
//      "projectID": 228832,
//      "fileID": 2645992,
//      "required": true
//    },
//    {
//      "projectID": 363993,
//      "fileID": 3140441,
//      "required": true
//    },
//    {
//      "projectID": 69118,
//      "fileID": 2664449,
//      "required": true
//    },
//    {
//      "projectID": 271298,
//      "fileID": 3287602,
//      "required": true
//    },
//    {
//      "projectID": 261251,
//      "fileID": 3045651,
//      "required": true
//    },
//    {
//      "projectID": 32274,
//      "fileID": 2916002,
//      "required": true
//    },
//    {
//      "projectID": 463445,
//      "fileID": 3256817,
//      "required": true
//    },
//    {
//      "projectID": 272450,
//      "fileID": 3748293,
//      "required": true
//    },
//    {
//      "projectID": 69162,
//      "fileID": 2920433,
//      "required": true
//    },
//    {
//      "projectID": 69163,
//      "fileID": 2926431,
//      "required": true
//    },
//    {
//      "projectID": 314904,
//      "fileID": 2819669,
//      "required": true
//    },
//    {
//      "projectID": 318255,
//      "fileID": 2919497,
//      "required": true
//    },
//    {
//      "projectID": 297038,
//      "fileID": 3475677,
//      "required": true
//    },
//    {
//      "projectID": 376351,
//      "fileID": 3015136,
//      "required": true
//    },
//    {
//      "projectID": 283644,
//      "fileID": 2694382,
//      "required": true
//    },
//    {
//      "projectID": 276951,
//      "fileID": 2880613,
//      "required": true
//    },
//    {
//      "projectID": 244559,
//      "fileID": 2830252,
//      "required": true
//    },
//    {
//      "projectID": 313816,
//      "fileID": 2851670,
//      "required": true
//    },
//    {
//      "projectID": 298187,
//      "fileID": 3005950,
//      "required": true
//    },
//    {
//      "projectID": 271384,
//      "fileID": 2920434,
//      "required": true
//    },
//    {
//      "projectID": 254629,
//      "fileID": 3425551,
//      "required": true
//    },
//    {
//      "projectID": 224472,
//      "fileID": 2969118,
//      "required": true
//    },
//    {
//      "projectID": 272514,
//      "fileID": 2962052,
//      "required": true
//    },
//    {
//      "projectID": 261348,
//      "fileID": 3143349,
//      "required": true
//    },
//    {
//      "projectID": 306028,
//      "fileID": 2833640,
//      "required": true
//    },
//    {
//      "projectID": 243478,
//      "fileID": 2745657,
//      "required": true
//    },
//    {
//      "projectID": 266936,
//      "fileID": 3487112,
//      "required": true
//    },
//    {
//      "projectID": 254652,
//      "fileID": 2591087,
//      "required": true
//    },
//    {
//      "projectID": 304920,
//      "fileID": 2669468,
//      "required": true
//    },
//    {
//      "projectID": 319441,
//      "fileID": 3536155,
//      "required": true
//    },
//    {
//      "projectID": 237903,
//      "fileID": 3330308,
//      "required": true
//    },
//    {
//      "projectID": 313866,
//      "fileID": 3657641,
//      "required": true
//    },
//    {
//      "projectID": 433389,
//      "fileID": 3508235,
//      "required": true
//    }
//  ],
//  "overrides": "overrides"
//}

// generate manifest file by org.json
fun generateManifest(
    packName: String,
    mcVersion: String,
    modLoaderVersion: String,
    mods: List<ModFileInfo>
): String {
    val manifest = JSONObject()
    manifest.put("manifestType", "minecraftModpack")
    manifest.put("manifestVersion", 1)
    manifest.put("name", packName)
    manifest.put("version", "1.0")
    manifest.put("author", "Author")
    manifest.put(
        "minecraft",
        JSONObject()
            .put("version", mcVersion)
            .put(
                "modLoaders", JSONArray().put(
                    JSONObject()
                        .put("id", modLoaderVersion)
                        .put("primary", true)
                )
            )
    )
    manifest.put("files", JSONArray().apply {
        mods.forEach {
            put(JSONObject().put("projectID", it.projectId).put("fileID", it.fileId).put("required", true))
        }
    })
    manifest.put("overrides", "overrides")
    return manifest.toString(4)
}