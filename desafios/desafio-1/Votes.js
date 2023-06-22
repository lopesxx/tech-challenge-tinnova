var assert = require('assert');

class Votes {
    constructor(voters_count) {
        this.voters_count = voters_count,
        this.valid = 0,
        this.blank = 0,
        this.null = 0;

        this.validPercent = () => this.valid / this.voters_count;
        this.blankPercent = () => this.blank / this.voters_count;
        this.nullPercent = () => this.null / this.voters_count;
    }
}

try {
    let votes = new Votes(1000);
    votes.valid = 800;
    votes.blank = 150;
    votes.null  = 50;

    assert.equal(0.8,  votes.validPercent());
    assert.equal(0.15, votes.blankPercent());
    assert.equal(0.05, votes.nullPercent());

    console.log(`Votes: ${votes.voters_count}`);
    console.log(`Valid: ${votes.valid} (${votes.validPercent()}%)`);
    console.log(`Blank: ${votes.blank} (${votes.blankPercent()}%)`);
    console.log(`Null: ${votes.blank} (${votes.nullPercent()}%)`);
} catch(e) {
    console.log(e);
}