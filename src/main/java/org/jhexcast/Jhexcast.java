package org.jhexcast;

public class Jhexcast {


    private static final String INIT_ACTION = "init";
    private static final String SEQUENCE_ACTION = "sequence";
    private static final String NEXT_ACTION = "next";

    protected String getAction(String[] args) {
        if(args.length > 0) {
            if(args[0].toLowerCase().equals(INIT_ACTION)) {
                return INIT_ACTION;
            }
            else if(args[0].toLowerCase().equals(SEQUENCE_ACTION)) {
                return SEQUENCE_ACTION;
            } else {
                return null;
            }
        }
        return NEXT_ACTION;
    }

    public void process(String[] arguments) {
        String action = this.getAction(arguments);
        Integer vecsize = null;
        Integer vecdim = null;

        if(arguments.length > 1) {
            vecsize = Integer.parseInt(arguments[1]);
        }
        if(arguments.length > 2) {
            vecdim = Integer.parseInt(arguments[2]);
        }

        switch (action) {
            case INIT_ACTION: this.performInitialize(vecdim, vecsize); return;
            case SEQUENCE_ACTION: this.performSequence(); return;
            case NEXT_ACTION: performNext(); return;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
    }

    protected void performSequence() {

    }

    protected void performNext() {

    }

    protected void performInitialize(Integer vectorDimension, Integer vectorSize) {

    }
}


//        initial_vals = {}
//            linear_size = int(vec_size) ** int(vec_dim)
//            initial_vals['size'] = vec_size
//            initial_vals['dimension'] = vec_dim
//            initial_vals['index'] = random.randint(0, linear_size)
//            initial_vals['leap'] = PRIMES.get(len(str(linear_size)), 7)
//            initial_vals['validate'] = int(vec_dim) == 8
//
//        HexConfig().initialize(initial_vals)
//        return
//
//    if is_seq_req:
//        if not os.path.exists(HexConfig.CONFIG_FILE):
//            no_init()
//            return
//
//        config = HexConfig()
//        hexinator = Hexinator(config)
//        seq = hexinator.get_sequence()
//        print(seq)
//        return

