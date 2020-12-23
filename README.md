# Advent of Code 2020

My solutions to the [Advent of Code 2020](https://adventofcode.com/2020) puzzles.

Most of the interesting stuff happens in each of the Day classes,
and the Main class gets written over to run the code.

Start: 12/02/2020 (yes, I started a day late)

End:

### Resources/Notes

Day 13

* [Chinese Remainder Theorem](https://crypto.stanford.edu/pbc/notes/numbertheory/crt.html)

Day 17

* may revisit this one to allow for infinite dimensions

Day 19

* this one was hard - getting the recursion right and debugging - and honestly, I made it harder by not looking at the input for shortcuts, though it might be better for adaptation to different inputs?

Day 20

* haven't done part 2 yet - will need a method to actually put the image together in a certain orientation, then a method
to flip/rotate the image and find the sea monsters, replace them, and count the rough water

Day 23

* initially did using lists which was fine for part 1
* part 2 not fast enough so switched to method using arrays where the index is the value and the value is the next value -
inspired by [this solution](https://topaz.github.io/paste/#XQAAAQDoDQAAAAAAAAA4GEiZzRd1JAhVfJF6m5TESt2beCj2kKu2/f4bRAk0mq/rn8IZmMagCWN7ccyloCBUpaxqfdI0YR2GuRbKQPUhHfZadbmLvAG0WoNk0aKBcLdlmqvI/m+K17zw8bwQkH9qucaAAAMIHldJIaEv3vwGHR8znv7Bby8RXg+kI3yx47jcCERIkgID8RZXt4GSxjepGZg/leHYzytxN49mz6C24uKk2cWNgLktIxlhhlOR+G8xnySkW+o0VdyMfMr6cwjpUrlF+X8VcaaB4ReuTZWXq4i8/7zM3+TGZMbQgrvF4FPTMj7gP09BADuom98pGt7gCcc/D0otAgO/3aEA2rD9Ns1E/85x6llm5TBKgSKxzl1Y4xH1dZwJanBUcU/xti/kZ0S+IEFVQOM/2cJgGv0q27plpNXPUqavNKFjSNEMSl8L9RT8JTwU6N1CmA5P4w/nlyiTbMzH8KwKYGUzXPHb4Yl4goV1gvgsWDBO7XwWQLPoKkP8sl6uM5lVhyKT1Z1T1KuvwT3HyXALbDL/istB7fgCqCfuxgzalMcE2Y2oh4OS58YW+bpsp9L6fWOIFfaP8b4mRFQinwaw45AD+O7sGBBuYfdNxDuvfNOqhFFFPZeSbsq3NF6nk2VfOkX8HZUWRx/KceNmsugHjOHDbx5XHwdOXgbKNID1ejqL80wnHoYd2mYGPDyPQ7C2Qo9lgeHcJZ9KeXc9G442LS3m5arE2f/l3dDCKXrDvC2qjBpedSF0ueCMC9SigrEjeKGHc6+PS7HfYAkbnQBnE8Tbwd1vLeeE3QY30TNfSKH1VxFjnEV0DZXiudt5ZFYVH0oglRNsmXoIk6RV8FFbd6o3l1+Ktgx9BxMabU3tN4d5L23pbmwujm13a9aXRlAjPFWRLysvqfUHHccKIbuhP/E4RzQZB6bZN1W4uGVUaB9KhMULSEZFHxwUDEHtxYkYQPglqqj9qu+8anFA4zHu/2uIkF58feNPK4BWbg41kZ1ZQef8KhMq09Lz/DPsoxG4SfEZI7QsVWDwjvz+ujIf0mUtIjkMESeVXNCwkw51yz9SBViPQy0Q/BclHb/dJEX4iKMoSYalk7ZnG8YcMUP1BP0Om8CiWauz1meq2LCKKmAkSPIEXZL9RQBv9Syvp+tFoFNYicTfLFRVc4Eb3S++1wX7IUT0xADw4+vKfSquBX/lkPbLl5Aw0xZM8/0xy2GWNBc76QZD7ESokAYizcmqwq7olvhrJ/9h3Sy3AHP6h7XusrwUrqW5D9DqqT29iaDCQXZet9nBfMxZGrxPi59Qc78TzAFdcZo3eVoXo7hM9yB0vIbIRZttkVOEGGsVQ4Vlyg7X7KEfOYd/TqQjxbwmxuGFdwczzsQ7KYAnlaqyp2+PL+q0TI8fQnY0G05UW9d//h+b42lQYU0NXmgEZC1r27rEJB78YBJthu1LRrlXGag+nNtbRhMf/58BEMQAKvTERKPKceCOWLN1i2xAnNRZsjqJludUslPe/lKfquXFNNCQnBVZUd09QZiPQRtnY53OX581fYFVQ7tNfIy3fwR+fEG5ei3uCNfUgz4XhRdS+YogLjxIA/osvhETZn76At8aczU7kH4n59QamLF1DoL96KHKdYmNg033KuBur8LFBHOhr6gkD+U8+ib/SaTSWk4RZn6nkjriZwahkNVPrwofZ+XltMU7ibkZag3yNhqMoLi00Y7rf/HNdYpMpFMTaau6EzqA6/oLFY8F3uU4f2Xwj6CqLNS4adu0Neo173uqXZ1phPAGAMjiDlVVSdbC9j+BXXj8Zxxv1WU3zvZXFGalHLyDRVlY4hJKWNexokfoseNysG+a9gfs4g5iK02hNQ99jKxz62CHwlKp4A3F6gcWtbL/yu+Ldg==)