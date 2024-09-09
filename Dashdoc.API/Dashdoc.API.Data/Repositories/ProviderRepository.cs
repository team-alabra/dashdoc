using Dashdoc.API.Domain.Abstract;
using Dashdoc.API.Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace Dashdoc.API.Data.Repositories;

public class ProviderRepository : IProviderRepository
{
    private readonly DashdocDbContext _dbContext;

    public ProviderRepository (DashdocDbContext dbContext)
    {
        _dbContext = dbContext;
    }

    public async Task<Provider?> GetByIdAsync(long providerId) =>
        await _dbContext.Provider.FirstOrDefaultAsync(e => e.Id == providerId);

    public async Task<Provider?> GetByEmailAsync(string providerEmail) =>
        await _dbContext.Provider.FirstOrDefaultAsync(e => e.Email == providerEmail);

    public async Task<Provider?> CreateAsync(Provider entityToCreate)
    {
        ArgumentNullException.ThrowIfNull(entityToCreate);

        entityToCreate.CreatedOn = DateTime.UtcNow;
        entityToCreate.LastUpdated = null;
        
        var addResult = await _dbContext.Provider.AddAsync(entityToCreate);

        await _dbContext.SaveChangesAsync();

        return addResult.Entity;
    }

    public async Task<Provider?> UpdateAsync(Provider entityToUpdate)
    {
        ArgumentNullException.ThrowIfNull(entityToUpdate);

        entityToUpdate.LastUpdated = DateTime.UtcNow;

        var updateResult = _dbContext.Provider.Update(entityToUpdate);
        await _dbContext.SaveChangesAsync();

        return updateResult.Entity;
    }

    public async Task<bool> DeleteAsync(long providerId)
    {
        var entityToDelete = await _dbContext.Provider.FirstOrDefaultAsync(e => e.Id == providerId);

        if (entityToDelete == null) return false;
        
        _dbContext.Provider.Remove(entityToDelete);
        await _dbContext.SaveChangesAsync();
            
        return true;
    }

    public IEnumerable<Provider> GetAllAgencyProvidersAsync(long agencyId) => 
         _dbContext.Provider.Where(e => e.AgencyId == agencyId);
}