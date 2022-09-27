package org.jhexcast;

import org.jhexcast.utils.Hexutils;

import java.util.*;

public class Hexinator {

    private int vectorSize;
    private int vectorDimension;
    private int leapDistance;
    private int currentIndex;
    private int linearSize;
    private List<Integer> tiers = null;
    private HexConfig config = null;


    public Hexinator(HexConfig config) {
        this.vectorSize = config.getSize();
        this.vectorDimension = config.getDimension();
        this.leapDistance = config.getLeap();
        this.currentIndex = config.getIndex();
        this.linearSize = (int) Math.pow(this.vectorSize, this.vectorDimension);
        this.config = config;

        //calculate tiers
        this.calculateTiers();
    }

    protected void calculateTiers() {
        this.tiers = new ArrayList<Integer>();
        for (int i = this.vectorDimension - 1; i >= 0; i--) {
            int tier = (int) Math.pow(this.vectorSize, i);
            this.tiers.add(tier);
        }
    }

    protected String getHexStringFromVector(int[] vector) {

        List<String> hexVector = new ArrayList<String>();

        for (int value: vector) {
            hexVector.add(Integer.toHexString(value));
        }
        String[] targ = new String[hexVector.size()];
        return String.join("", hexVector.toArray(targ)).toUpperCase();
    }

    protected int[] getVectorFromIndex(int index) {
        int vecDim = this.vectorDimension;
        int[] extracted = new int[vecDim];
        Arrays.fill(extracted, 0);
        int tierItem = index;

        for (int ci = 0; ci < vecDim - 1; ci++) {
            extracted[ci] = (int) Math.floor(tierItem / (double) this.tiers.get(ci));
            tierItem = tierItem - extracted[ci] * this.tiers.get(ci);
        }
        extracted[vecDim-1] = tierItem - extracted[vecDim-1] * this.tiers.get(vecDim-2);
        return extracted;
    }

    protected int getIncrementedIndex() {
        System.out.println("cur:" + this.currentIndex);
        this.currentIndex = (this.currentIndex + this.leapDistance) % this.linearSize;
        this.config.setIndex(this.currentIndex);
        System.out.println("nex:" + this.currentIndex);
        return this.currentIndex;
    }

    protected String getNextHexString() {
        int nextIndex = this.getIncrementedIndex();
        int[] nextVec = this.getVectorFromIndex(nextIndex);
        String nextHex = this.getHexStringFromVector(nextVec);
        return nextHex;
    }

    protected String getNextValidHexString() throws HexinatorMaxTriesException {
        String nextHS = this.getNextHexString();
        if(! this.config.getValidate()) {
            return nextHS;
        }
        HexValidator validator = new HexValidator();
        int tries = 1;

        while (!validator.isValid(nextHS) && tries < HexValidator.MAX_TRIES) {
            nextHS = this.getNextHexString();
        }
        if(!validator.isValid(nextHS)) {
            throw new HexinatorMaxTriesException();
        }
        return nextHS;
    }

    /**
     * Returns the sequence for the next pseudo-random number to be emitted.
     * Will only works for sub 1000 lists
     *
     * @return
     */
    protected LinkedHashMap<String, Integer> getSequence() {
        int linSize = this.linearSize;
//        List<Integer> nsequence = new ArrayList<>();
//        List<String> csequence = new ArrayList<>();

        if (linSize <  1000) {
            int leap = this.leapDistance;
            int ci = this.config.getStartIndex();
            String cc = Hexutils.joinedString(this.getVectorFromIndex(ci));

            LinkedHashMap<String, Integer> seq = new LinkedHashMap<>();
            seq.put(cc, ci);
//            nsequence.add(ci);
//            csequence.add(cc);
            for (int i = 0; i < linSize - 1; i++) {
                ci = (ci + leap) % linSize;
                cc = Hexutils.joinedString(this.getVectorFromIndex(ci));
//                nsequence.add(ci);
//                csequence.add(cc);
                seq.put(cc, ci);
            }
            return seq;
        }
        return null;
    }
}

//
//
////
//    def get_sequence(self):
//        """
//        Returns the sequence for the next 'random' nmber to be emitted
//        :return:
//        """
//        print(self.tiers)
//        lins = self.config.get_linear_size()
//        nseq = []
//        cseq = []
//        # arbitrary but seems reasonable for this
//        if lins < 1000:
//            ld = self.config.get_leap()
//            ci = self.config.get_startindex()
//            cc = ''.join([str(d) for d in self._get_vector_from_index(ci)])
//            seq = {cc: ci}
//            nseq.append(ci)
//            cseq.append(cc)
//            for i in range(lins-1):
//                ci = (ci + ld) % lins
//                cc = ''.join([str(d) for d in self._get_vector_from_index(ci)])
//                seq[cc] = ci
//                nseq.append(ci)
//                cseq.append(cc)
//            return seq

/////////////////////////////////////////////////////////////////

//    def _get_vector_from_index(self, index):
//        vec_dim = self.vector_dimension
//        extracted = [0] * vec_dim
//        tier_item = index
//        for ci in range(vec_dim - 1):
//            extracted[ci] = math.floor(tier_item / self.tiers[ci])
//            tier_item = tier_item - extracted[ci] * self.tiers[ci]
//        extracted[vec_dim - 1] = tier_item - extracted[vec_dim - 1] * self.tiers[vec_dim - 2]
//        return extracted
